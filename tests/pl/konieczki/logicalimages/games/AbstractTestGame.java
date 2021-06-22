package pl.konieczki.logicalimages.games;

import lombok.NonNull;
import org.junit.Assert;
import org.junit.Test;
import pl.konieczki.logicalimages.LogicalImageValidator;
import pl.konieczki.logicalimages.ResultFinder;
import pl.konieczki.logicalimages.ResultFinderStatistics;
import pl.konieczki.logicalimages.model.Game;

public abstract class AbstractTestGame {

    protected final ResultFinder resultFinder = new ResultFinder();

    protected abstract Game prepareGame();

    protected void addHints(@NonNull Game game) {
        // do nothing
    }

    @Test
    public void runTest() {
        final Game game = prepareGame();
        addHints(game);
        var statistics = new ResultFinderStatistics();
        try {
            this.resultFinder.findResult(game, statistics);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.print(game);
        if (statistics.getCounter() > 0)
            System.out.println("Founded in " + statistics.getCounter() + " steps");
        System.out.println("=========================================================================");
        Assert.assertTrue(game.isFullMarked());
        Assert.assertTrue(new LogicalImageValidator().validate(game));
    }
}
