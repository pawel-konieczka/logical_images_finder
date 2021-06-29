package pl.konieczki.logicalimages.strategies;

import lombok.Getter;
import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

public abstract class AbstractStrategy implements FindResultStrategy {

    @Getter
    protected final GameFieldTranslator translator;
    protected boolean noMoreChangesPossible = false;

    protected AbstractStrategy(@NonNull GameFieldTranslator translator) {
        this.translator = translator;
    }

    @Override
    public boolean find(@NonNull Game game) {
        if (noMoreChangesPossible)
            return false;
        if (!checkIfStrategyIsApplicable(game)) {
            return markNoMoreChangesPossibility();
        }
        return internalFind(game);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + translator + ")";
    }

    protected abstract boolean internalFind(@NonNull Game game);

    protected abstract boolean checkIfStrategyIsApplicable(@NonNull Game game);

    protected boolean markNoMoreChangesPossibility() {
        this.noMoreChangesPossible = true;
        return false;
    }

    protected boolean setAllFieldsInRangeTo(
            int startField, int endField, @NonNull FieldState fieldState, @NonNull Game game
    ) {
        return translator.setAllFieldsInRangeTo(game, startField, endField, fieldState);
    }
}