package pl.konieczki.logicalimages;

import org.junit.Assert;
import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldsSequences;

public class LogicalImageValidatorTest {

    @Test
    public void validate_letter_n_with_success() {
        final var gameString = "" +
                "X   X\n" +
                "XX  X\n" +
                "X X X\n" +
                "X  XX\n" +
                "X   X\n";
        final var game = new LogicalImageDecoder().parseFromString(5, 5, gameString);
        game.setHorizontalSequences(1, new FieldsSequences(1, 1));
        game.setHorizontalSequences(2, new FieldsSequences(2, 1));
        game.setHorizontalSequences(3, new FieldsSequences(1, 1, 1));
        game.setHorizontalSequences(4, new FieldsSequences(1, 2));
        game.setHorizontalSequences(5, new FieldsSequences(1, 1));
        game.setVerticalSequences(1, new FieldsSequences(5));
        game.setVerticalSequences(2, new FieldsSequences(1));
        game.setVerticalSequences(3, new FieldsSequences(1));
        game.setVerticalSequences(4, new FieldsSequences(1));
        game.setVerticalSequences(5, new FieldsSequences(5));
        final var result = new LogicalImageValidator().validate(game);
        Assert.assertTrue(result);
    }

    @Test
    public void validate_letter_n_with_failure() {
        final var gameString = "" +
                "X   X\n" +
                "XX  X\n" +
                "X X X\n" +
                "X  XX\n" +
                "X   X\n";
        final var game = new LogicalImageDecoder().parseFromString(5, 5, gameString);
        game.setHorizontalSequences(1, new FieldsSequences(1, 1));
        game.setHorizontalSequences(2, new FieldsSequences(2, 1));
        game.setHorizontalSequences(3, new FieldsSequences(1, 1, 1));
        game.setHorizontalSequences(4, new FieldsSequences(1, 2));
        game.setHorizontalSequences(5, new FieldsSequences(1));
        game.setVerticalSequences(1, new FieldsSequences(5));
        game.setVerticalSequences(2, new FieldsSequences(1));
        game.setVerticalSequences(3, new FieldsSequences(1));
        game.setVerticalSequences(4, new FieldsSequences(1));
        game.setVerticalSequences(5, new FieldsSequences(3));
        final var result = new LogicalImageValidator().validate(game);
        Assert.assertFalse(result);
    }
}