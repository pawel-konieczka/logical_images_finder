package pl.konieczki.logicalimages;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.strategies.*;
import pl.konieczki.logicalimages.translator.ColumnsGameFieldTranslator;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

import java.util.ArrayList;
import java.util.List;

public class ResultFinder {

    private final List<FindResultStrategy> strategies = new ArrayList<>();

    public void findResult(
            @NonNull Game game, @NonNull ResultFinderStatistics statistics
    ) throws ResultFinderException {
        initStrategies(game);
        statistics.init();
        boolean stillSearching = !strategies.isEmpty();
        try {
            while (stillSearching) {
                stillSearching = false;
                for (FindResultStrategy strategy : this.strategies) {
                    final boolean b = strategy.find(game);
                    statistics.process(strategy, b);
                    stillSearching = stillSearching || b;
                }
            }
        } catch (Exception ex) {
            throw new ResultFinderException("Unable to find result (counter: " + statistics.getCounter() + ")", ex);
        }
    }

    public static class ResultFinderException extends Exception {
        ResultFinderException(String message, Throwable parent) {
            super(message, parent);
        }
    }

    private void initStrategies(@NonNull Game game) {
        this.strategies.clear();
        for (var w = 1; w <= game.getRowCount(); w++)
            internalStrategiesInit(new RowsGameFieldTranslator(w));
        for (var k = 1; k <= game.getColCount(); k++)
            internalStrategiesInit(new ColumnsGameFieldTranslator(k));
    }

    private void internalStrategiesInit(@NonNull GameFieldTranslator translator) {
        this.strategies.add(new RecalculateFieldsRangeAndSequencesStrategy(translator)); // must be first
        this.strategies.add(new MarkAsEmptyWhenNoSequencesStrategy(translator));
        this.strategies.add(new MarkCommonFieldsStrategy(translator));
        this.strategies.add(new MarkFirstSequenceWhenFirstFullFieldIsInRangeOfSequenceStrategy(translator));
        this.strategies.add(new MarkSequencesUsingSequenceCountStrategy(translator));
        this.strategies.add(new MarkFirstSureSequenceStrategy(translator));
        this.strategies.add(new MarkUnreachableFieldsStrategy(translator));
        this.strategies.add(new MarkCompletedSequencesBordersStrategy(translator));
        this.strategies.add(new ExpandSequencesBordersForFullFieldsStrategy(translator));
        this.strategies.add(new MarkAsEmptyWhenRangeTooSmallStrategy(translator));
        this.strategies.add(new MarkAsEmptyUndefinedFieldsBetweenCompletedSequencesStrategy(translator));
        this.strategies.add(new MarkAsFullSequenceRangeStrategy(translator));
    }
}