package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import org.junit.Assert;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.model.Game;

public abstract class AbstractTestClassForStrategies {

    protected final AbstractStrategy strategia;

    public AbstractTestClassForStrategies(@NonNull AbstractStrategy strategia) {
        this.strategia = strategia;
    }

    protected abstract Game createGame(@NonNull String input);

    protected abstract String formatResult(@NonNull String input);

    protected abstract void setupSequences(@NonNull Game game, FieldSequence... sequences);

    protected abstract void setupFields(@NonNull Game game, @NonNull String input);

    protected void baseTrueTest(@NonNull String input, @NonNull String expected, FieldSequence... sequences) {
        baseTrueTest(input, expected, false, sequences);
    }

    protected void baseTrueTest(
            @NonNull String input, @NonNull String expected, boolean recalculateFieldsRangesAndSequences,
            FieldSequence... sequences
    ) {
        // arrange
        final Game game = createGame(input);
        setupSequences(game, sequences);
        setupFields(game, input);
        if (recalculateFieldsRangesAndSequences)
            this.strategia.getTranslator().recalculateFieldsRangeAndSequences(game);
        validateInput(game, input);
        // act
        final boolean b = runStrategy(game);
        // assert
        Assert.assertTrue(b);
        validateOutput(game, expected);
    }

    protected void baseFalseTest(@NonNull String input, FieldSequence... sequences) {
        // arrange
        final Game game = createGame(input);
        setupSequences(game, sequences);
        setupFields(game, input);
        validateInput(game, input);
        // act
        final boolean b = runStrategy(game);
        // assert
        Assert.assertFalse(b);
        validateOutput(game, input);
    }

    protected void validateInput(@NonNull Game game, @NonNull String input) {
        Assert.assertEquals(formatResult(input), game.toString());
    }

    protected void validateOutput(@NonNull Game game, @NonNull String input) {
        Assert.assertEquals(formatResult(input), game.toString());
    }

    protected boolean runStrategy(@NonNull Game game) {
        return strategia.find(game);
    }
}