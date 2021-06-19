package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.*;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Strategia dla dwóch sąsiadujących kompletnych ciągów ustawia wszystkie pola pomiędzy jako puste.
 * Strategia polowa.
 */
public class MarkAsEmptyUndefinedFieldsBetweenCompletedSequencesStrategy extends AbstractStrategy {

    public MarkAsEmptyUndefinedFieldsBetweenCompletedSequencesStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean internalFind(@NonNull Game game) {
        final var sequences = translator.getSequences(game);
        if (sequences.isEmpty())
            return markNoMoreChangesPossibility();
        var anyChange = false;
        final var fieldsRange = translator.getFieldsRange(game);
        for (var i = 0; i < sequences.getCount(); i++) {
            final var sequence = sequences.getSequence(i);
            if (!sequence.isCompleted()) // tylko dla kompletnych
                continue;
            final boolean b = processCompletedSequence(game, sequences, fieldsRange, i);
            anyChange = anyChange || b;
        }
        return anyChange;
    }

    private boolean processCompletedSequence(
            Game game, FieldsSequences sequences, FieldsRange fieldsRange, int sequenceIdx
    ) {
        var anyChange = false;
        FieldSequence completedSequence = sequences.getSequence(sequenceIdx);
        if (sequenceIdx == 0) { // dla pierwszego sprawdzamy od lewego brzegu
            anyChange = setAllFieldsInRangeTo(fieldsRange.getFieldFrom(), completedSequence.getFieldFrom() - 1, FieldState.EMPTY, game);
        } else {
            // dla tych w środku sprawdzamy względem poprzednika (tez musi byc kompletny)
            final var previousSequence = sequences.getSequence(sequenceIdx - 1);
            if (previousSequence.isCompleted()) {
                anyChange = setAllFieldsInRangeTo(previousSequence.getFieldTo() + 1, completedSequence.getFieldFrom() - 1, FieldState.EMPTY, game);
            }
        }
        if (sequenceIdx == sequences.getCount() - 1 && (completedSequence.getFieldTo() < fieldsRange.getFieldTo())) { // dla ostatniego sprawdzamy do prawego brzegu
            final boolean b = setAllFieldsInRangeTo(completedSequence.getFieldTo() + 1, fieldsRange.getFieldTo(), FieldState.EMPTY, game);
            anyChange = anyChange || b;
        }
        return anyChange;
    }
}