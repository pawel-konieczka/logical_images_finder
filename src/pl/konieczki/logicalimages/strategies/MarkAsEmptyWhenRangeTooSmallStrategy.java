package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsRange;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Strategia oznacza jako puste pole wszystkie te nieoznaczone ciągi pól, które są krótsze od
 * najkrótszego dostępnego ciągu.
 * Strategia polowa.
 */
public class MarkAsEmptyWhenRangeTooSmallStrategy extends AbstractStrategy {

    public MarkAsEmptyWhenRangeTooSmallStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean internalFind(Game game) {
        final var sequences = translator.getSequences(game);
        if (sequences.isEmpty()) {
            return markNoMoreChangesPossibility();
        }
        // "XX   ? X?? X"|2[1:2*],2[8:8],1[12:12*] -> "XX     X?? X"
        // "XX? ??"|3[1:2] -> "XX?   "
        final int minSequenceLength = findLengthOfShortenUncompletedSequence(sequences);
        if (minSequenceLength < 2) // brak lub jedynki pomijamy
            return false;
        final var fieldsRange = translator.getFieldsRange(game);
        var anyChange = false;
        int startFieldIdx = findFirstUndefinedFieldFromIndex(fieldsRange.getFieldFrom(), game, fieldsRange);
        while (startFieldIdx > 0) {
            final int endFieldIdx = findFirstEmptyFieldFromIndex(startFieldIdx, game, fieldsRange);
            if ((endFieldIdx > 0)
                    && (endFieldIdx - startFieldIdx + 1 < minSequenceLength)
            ) {
                final boolean b = setAllFieldsInRangeTo(startFieldIdx, endFieldIdx, FieldState.EMPTY, game);
                anyChange = anyChange || b;
                startFieldIdx = endFieldIdx + 1;
            } else {
                startFieldIdx++;
            }
            startFieldIdx = findFirstUndefinedFieldFromIndex(startFieldIdx, game, fieldsRange);
        }
        return anyChange;
    }

    private int findFirstEmptyFieldFromIndex(int startFieldIdx, Game game, FieldsRange fieldsRange) {
        if (startFieldIdx < fieldsRange.getFieldFrom())
            return FieldsRange.UNDEFINED;
        for (int p = startFieldIdx; p <= fieldsRange.getFieldTo(); p++) {
            final var stanPola = translator.getFieldStateInRange(game, p);
            if (FieldState.FULL == stanPola)
                return FieldsRange.UNDEFINED;
            if (FieldState.EMPTY == stanPola)
                return p - 1;
        }
        return fieldsRange.getFieldTo();
    }

    private int findFirstUndefinedFieldFromIndex(
            int startFieldIDx, Game game, FieldsRange fieldsRange
    ) {
        for (int p = startFieldIDx; p <= fieldsRange.getFieldTo(); p++)
            if (FieldState.UNDEFINED == translator.getFieldStateInRange(game, p)
                    // nieokreślona jako pierwsza lub po pustej
                    && (p == fieldsRange.getFieldFrom() || FieldState.EMPTY == translator.getFieldStateInRange(game, p - 1))
            )
                return p;
        return FieldsRange.UNDEFINED;
    }

    private int findLengthOfShortenUncompletedSequence(FieldsSequences fieldsSequences) {
        int min = -1;
        for (var i = 0; i < fieldsSequences.getCount(); i++) {
            final var sequence = fieldsSequences.getSequence(i);
            if (!sequence.isCompleted() && (min == -1 || sequence.getLength() < min))
                min = sequence.getLength();
        }
        return min;
    }
}