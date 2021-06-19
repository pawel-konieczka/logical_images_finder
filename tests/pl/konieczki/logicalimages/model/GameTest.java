package pl.konieczki.logicalimages.model;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void planszaToString() {
        // Assert
        final Game game = new Game(10, 5);
        game.setVerticalSequences(1, new FieldsSequences(4, 4));
        game.setVerticalSequences(2, new FieldsSequences(4, 14));
        game.setVerticalSequences(3, new FieldsSequences(8));
        game.setVerticalSequences(4, new FieldsSequences(16));
        game.setVerticalSequences(5, new FieldsSequences(14));

        game.setHorizontalSequences(1, new FieldsSequences(4));
        game.setHorizontalSequences(2, new FieldsSequences(6));
        game.setHorizontalSequences(3, new FieldsSequences(8));
        game.setHorizontalSequences(4, new FieldsSequences(23));
        game.setHorizontalSequences(5, new FieldsSequences(19, 3));
        game.setHorizontalSequences(6, new FieldsSequences(20, 3));
        game.setHorizontalSequences(7, new FieldsSequences(4, 3, 1, 13));
        game.setHorizontalSequences(8, new FieldsSequences(1, 1, 1, 1, 1, 1, 8));
        game.setHorizontalSequences(9, new FieldsSequences(6));
        game.setHorizontalSequences(10, new FieldsSequences(8));
        final String expected =
                "?????|4[-1:-1]\n" +
                        "?????|6[-1:-1]\n" +
                        "?????|8[-1:-1]\n" +
                        "?????|23[-1:-1]\n" +
                        "?????|19[-1:-1],3[-1:-1]\n" +
                        "?????|20[-1:-1],3[-1:-1]\n" +
                        "?????|4[-1:-1],3[-1:-1],1[-1:-1],13[-1:-1]\n" +
                        "?????|1[-1:-1],1[-1:-1],1[-1:-1],1[-1:-1],1[-1:-1],1[-1:-1],8[-1:-1]\n" +
                        "?????|6[-1:-1]\n" +
                        "?????|8[-1:-1]\n" +
                        "-----\n" +
                        "448AB\n" +
                        "4B   \n" +
                        "     \n" +
                        "A - 16, B - 14\n";
        // Arrange
        final String result = game.toString();
        // Assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void rowToStringTest() {
        final Game game = new Game(5, 5);
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
        game.setField(3, 1, FieldState.FULL);
        game.setField(3, 2, FieldState.EMPTY);
        game.setField(3, 3, FieldState.FULL);
        game.setField(3, 4, FieldState.EMPTY);
        game.setField(3, 5, FieldState.FULL);
        final FieldsSequences sequences = game.getHorizontalSequencesAt(3);
        sequences.getSequence(0).setFieldFrom(1);
        sequences.getSequence(1).setFieldFrom(3);
        sequences.getSequence(2).setFieldFrom(5);
        // Act
        final String result = game.rowToString(3);
        // assert
        Assert.assertEquals("X X X|1[1:1*],1[3:3*],1[5:5*]", result);
    }

    @Test
    public void colToStringTest() {
        final Game game = new Game(5, 5);
        game.setHorizontalSequences(1, new FieldsSequences(1, 1));
        game.setHorizontalSequences(2, new FieldsSequences(2, 1));
        game.setHorizontalSequences(3, new FieldsSequences(1, 1, 1));
        game.setHorizontalSequences(4, new FieldsSequences(1, 2));
        game.setHorizontalSequences(5, new FieldsSequences(1, 1));
        game.setVerticalSequences(1, new FieldsSequences(new FieldSequence(1, 5, 1, 5)));
        game.setVerticalSequences(2, new FieldsSequences(new FieldSequence(1, 1, 2, 2)));
        game.setVerticalSequences(3, new FieldsSequences(1));
        game.setVerticalSequences(4, new FieldsSequences(1));
        game.setVerticalSequences(5, new FieldsSequences(5));
        game.setField(1, 1, FieldState.FULL);
        game.setField(2, 1, FieldState.FULL);
        game.setField(3, 1, FieldState.FULL);
        game.setField(4, 1, FieldState.FULL);
        game.setField(5, 1, FieldState.FULL);
        game.setField(1, 2, FieldState.EMPTY);
        game.setField(2, 2, FieldState.FULL);
        game.setField(3, 2, FieldState.EMPTY);
        game.setField(4, 2, FieldState.EMPTY);
        game.setField(5, 2, FieldState.EMPTY);

        final String column1 = game.columnToString(1);
        Assert.assertEquals("XXXXX|5[1:5*]", column1);
        final String column2 = game.columnToString(2);
        Assert.assertEquals(" X   |1[2:2*]", column2);
    }
}