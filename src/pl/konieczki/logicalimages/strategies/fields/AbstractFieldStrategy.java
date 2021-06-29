package pl.konieczki.logicalimages.strategies.fields;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.strategies.AbstractStrategy;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

public abstract class AbstractFieldStrategy extends AbstractStrategy {

    protected AbstractFieldStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean checkIfStrategyIsApplicable(@NonNull Game game) {
        return !translator.checkIfAllFieldsMarkedInRange(game);
    }
}