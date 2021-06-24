package pl.konieczki.logicalimages.strategies.fields;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.strategies.AbstractStrategy;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * W ramach wiersza/kolumny oznacza wszystkie skrajne na lewo i prawo/od góry i dołu
 * jako zawsze puste dla pól spoza zakresu dostępności.
 */
public class SetAsEmptyUnreachableFieldsStrategy extends AbstractStrategy {

    public SetAsEmptyUnreachableFieldsStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean internalFind(@NonNull Game game) {
        final var sequences = translator.getSequences(game);
        if (sequences.isEmpty())
            return markNoMoreChangesPossibility();
        final var b1 = markUnreachableFieldsFromBeginning(sequences, game);
        final var b2 = markUnreachableFieldsFromEnding(sequences, game);
        return b1 || b2;
    }

    private boolean markUnreachableFieldsFromBeginning(FieldsSequences fieldsSequences, Game game) {
        var anyChanges = false;
        for (var i = 0; i < fieldsSequences.getCount(); i++) {
            final var sequence = fieldsSequences.getSequence(i);
            if (!sequence.isIdentified())
                return anyChanges;
            final int startFieldIdx;
            if (i == 0) { // pierwszy ciąg ogranicza sie do początkowego brzegu
                // ???XX|3 -> "  ?XX" == 5 - 3 = 2
                startFieldIdx = translator.getFieldsRangeStart(game);
            } else { // kolejny w ciągu
                // X?????X|2,3 -> X?  ??X
                final var previousSequence = fieldsSequences.getSequence(i - 1);
                startFieldIdx = previousSequence.getFieldFrom() + previousSequence.getLength();
            }
            final int endFieldIdx = sequence.getFieldTo() - sequence.getLength();
            final boolean b = setAllFieldsInRangeTo(startFieldIdx, endFieldIdx, FieldState.EMPTY, game);
            anyChanges = anyChanges || b;
        }
        return anyChanges;
    }

    private boolean markUnreachableFieldsFromEnding(FieldsSequences fieldsSequences, Game game) {
        var anyChanges = false;
        for (int i = fieldsSequences.getCount() - 1; i >= 0; i--) {
            final var sequence = fieldsSequences.getSequence(i);
            if (!sequence.isIdentified())
                return anyChanges;
            final int endFieldIdx;
            if (i == fieldsSequences.getCount() - 1) { // ostatni ciąg ogranicza sie do końcowego brzegu
                // ???XX|3 -> "  ?XX" == 5 - 3 = 2
                endFieldIdx = translator.getFieldsRangeEnd(game);
            } else { // kolejny w ciągu
                // X?????X|2,3 -> X?  ??X
                final var nextSequence = fieldsSequences.getSequence(i + 1);
                endFieldIdx = nextSequence.getFieldTo() - nextSequence.getLength();
            }
            final var startFieldIdx = sequence.getFieldFrom() + sequence.getLength();
            final boolean b = setAllFieldsInRangeTo(startFieldIdx, endFieldIdx, FieldState.EMPTY, game);
            anyChanges = anyChanges || b;
        }
        return anyChanges;
    }
}