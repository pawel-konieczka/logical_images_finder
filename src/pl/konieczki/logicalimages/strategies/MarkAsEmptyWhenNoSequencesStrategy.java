package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Jeśli wiersz/kolumna jest bez zdefiniowanych ciągów, to cały wiersz/kolumnę oznacza jako pusty.
 * Strategia polowa.
 */
public class MarkAsEmptyWhenNoSequencesStrategy extends AbstractStrategy {

    public MarkAsEmptyWhenNoSequencesStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean internalFind(@NonNull Game game) {
        if (!translator.checkSequencesExistence(game)) {
            return markNoMoreChangesPossibility();
        }
        this.noMoreChangesPossible = true;
        return translator.setAllFieldsInRangeTo(game, FieldState.EMPTY);
    }
}
