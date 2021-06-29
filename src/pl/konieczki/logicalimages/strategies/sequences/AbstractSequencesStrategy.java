package pl.konieczki.logicalimages.strategies.sequences;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.strategies.AbstractStrategy;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

public abstract class AbstractSequencesStrategy extends AbstractStrategy {

    protected AbstractSequencesStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean checkIfStrategyIsApplicable(@NonNull Game game) {
        return !translator.getSequences(game).checkIfAllSequencesAreCompleted();
    }
}