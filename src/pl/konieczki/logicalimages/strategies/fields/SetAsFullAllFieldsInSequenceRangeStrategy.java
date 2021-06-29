package pl.konieczki.logicalimages.strategies.fields;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Dla każdego zidentyfikowanego ciągu oznacza wszystkie pola jako pełne wewnątrz zakresu pól.
 * Strategia polowa.
 */
public class SetAsFullAllFieldsInSequenceRangeStrategy extends AbstractFieldStrategy {

    public SetAsFullAllFieldsInSequenceRangeStrategy(@NonNull GameFieldTranslator translator) {
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
            if (sequence.isIdentified())
                anyChange = translator.setAllFieldsInRangeTo(game, sequence, FieldState.FULL) || anyChange;
        }
        return anyChange;
    }
}