package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Wyznacza, o ile to możliwe, zawężony zakres pól i kompletnych ciągów, umożliwiając przetwarzanie coraz węższych
 * zakresów.
 * Strategia pól i ciągów.
 */
public class RecalculateFieldsRangeAndSequencesStrategy extends AbstractStrategy {

    public RecalculateFieldsRangeAndSequencesStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean internalFind(@NonNull Game game) {
        return translator.recalculateFieldsRangeAndSequences(game);
    }

    @Override
    protected boolean checkIfAllFieldsProperlyMarked(@NonNull Game game) {
        final boolean b1 = translator.checkIfAllFieldsMarkedInRange(game);
        final boolean b2 = translator.getSequences(game).checkIfAllSequencesAreCompleted();
        return b1 && b2;
    }
}
