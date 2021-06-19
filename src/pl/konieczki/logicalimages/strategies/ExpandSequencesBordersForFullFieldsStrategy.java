package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Dla każdego częściowo zidentyfikowanego ciągu poszerza jego granice według pełnych pól.
 * Strategia ciągowa.
 */
public class ExpandSequencesBordersForFullFieldsStrategy extends AbstractStrategy {

    public ExpandSequencesBordersForFullFieldsStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean internalFind(@NonNull Game game) {
        final var sequences = translator.getSequences(game);
        if (sequences.isEmpty())
            return markNoMoreChangesPossibility();
        var anyChange = false;
        for (var i = 0; i < sequences.getCount(); i++) {
            final var sequence = sequences.getSequence(i);
            if (!sequence.isIdentified() || sequence.isCompleted())
                continue;
            final var b1 = updateFieldFrom(sequence, game);
            final var b2 = updateFieldTo(sequence, game);
            anyChange = anyChange || b1 || b2;
        }
        return anyChange;
    }

    @Override
    protected boolean checkIfAllFieldsProperlyMarked(@NonNull Game game) {
        return translator.getSequences(game).checkIfAllSequencesAreCompleted();
    }

    private boolean updateFieldFrom(FieldSequence sequence, Game game) {
        final var fieldsRangeStart = translator.getFieldsRangeStart(game);
        for (var p = sequence.getFieldFrom() - 1; p >= fieldsRangeStart; p--) {
            if (translator.getFieldStateInRange(game, p) != FieldState.FULL) {
                return sequence.setFieldFrom(p + 1);
            }
        }
        return sequence.getFieldFrom() > fieldsRangeStart && sequence.setFieldFrom(fieldsRangeStart);
    }

    private boolean updateFieldTo(FieldSequence sequence, Game game) {
        final var fieldsRangeEnd = translator.getFieldsRangeEnd(game);
        for (var p = sequence.getFieldTo() + 1; p <= fieldsRangeEnd; p++) {
            if (translator.getFieldStateInRange(game, p) != FieldState.FULL) {
                return sequence.setFieldTo(p - 1);
            }
        }
        return sequence.getFieldTo() < fieldsRangeEnd && sequence.setFieldTo(fieldsRangeEnd);
    }
}