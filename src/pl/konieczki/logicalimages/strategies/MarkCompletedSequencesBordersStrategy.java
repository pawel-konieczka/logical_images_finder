package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Dla kompletnych ciągów oznacza jako puste pierwsze pole przed i pierwsze pole po ciągu.
 * Strategia polowa.
 */
public class MarkCompletedSequencesBordersStrategy extends AbstractStrategy {

    public MarkCompletedSequencesBordersStrategy(@NonNull GameFieldTranslator translator) {
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
            if (sequence.isCompleted()) {
                if (sequence.getFieldFrom() > fieldsRange.getFieldFrom()) { // pełne pole nie jest od brzegu początkowego
                    final var b = translator.setFieldStateInRange(game, sequence.getFieldFrom() - 1, FieldState.EMPTY);
                    anyChange = anyChange || b;
                }
                if (sequence.getFieldTo() < fieldsRange.getFieldTo()) { // pełne pole nie jest od brzegu końcowego
                    final var b = translator.setFieldStateInRange(game, sequence.getFieldTo() + 1, FieldState.EMPTY);
                    anyChange = anyChange || b;
                }
            }
        }
        return anyChange;
    }
}