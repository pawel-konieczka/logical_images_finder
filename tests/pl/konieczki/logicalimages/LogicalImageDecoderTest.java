package pl.konieczki.logicalimages;

import org.junit.Assert;
import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.Game;

public class LogicalImageDecoderTest {

    @Test
    public void simple_decode_test() {
//        X   X|1[1:1*],1[5:5*]
//        XX  X|2[1:2*],1[5:5*]
//        X X X|1[1:1*],1[3:3*],1[5:5*]
//        X  XX|1[1:1*],2[4:5*]
//        X   X|1[1:1*],1[5:5*]
//        -----
//        51115
        final var input = new Game(5, 5);
        input.setAllFieldsInRangeTo(1, 5, 1, 1, FieldState.FULL);
        input.setAllFieldsInRangeTo(1, 5, 5, 5, FieldState.FULL);
        input.setAllFieldsInRangeTo(1, 1, 2, 4, FieldState.EMPTY);
        input.setField(2, 2, FieldState.FULL);
        input.setAllFieldsInRangeTo(2, 2, 3, 4, FieldState.EMPTY);
        input.setField(3, 2, FieldState.EMPTY);
        input.setField(3, 3, FieldState.FULL);
        input.setField(3, 4, FieldState.EMPTY);
        input.setAllFieldsInRangeTo(4, 4, 2, 3, FieldState.EMPTY);
        input.setField(4, 4, FieldState.FULL);
        input.setAllFieldsInRangeTo(5, 5, 2, 4, FieldState.EMPTY);
        Assert.assertTrue(input.isFullMarked());

        final var result = new LogicalImageDecoder().decode(input);

        Assert.assertEquals("1,1", result.getHorizontalSequencesAt(1).toString());
        Assert.assertEquals("2,1", result.getHorizontalSequencesAt(2).toString());
        Assert.assertEquals("1,1,1", result.getHorizontalSequencesAt(3).toString());
        Assert.assertEquals("1,2", result.getHorizontalSequencesAt(4).toString());
        Assert.assertEquals("1,1", result.getHorizontalSequencesAt(5).toString());
        Assert.assertEquals("5", result.getVerticalSequencesAt(1).toString());
        Assert.assertEquals("1", result.getVerticalSequencesAt(2).toString());
        Assert.assertEquals("1", result.getVerticalSequencesAt(3).toString());
        Assert.assertEquals("1", result.getVerticalSequencesAt(4).toString());
        Assert.assertEquals("5", result.getVerticalSequencesAt(5).toString());
    }

    @Test
    public void simple_parse_from_string_test() {
//        X   X|1[1:1*],1[5:5*]
//        XX  X|2[1:2*],1[5:5*]
//        X X X|1[1:1*],1[3:3*],1[5:5*]
//        X  XX|1[1:1*],2[4:5*]
//        X   X|1[1:1*],1[5:5*]
//        -----
//        51115
        final var input = "" +
                "X   X\n" +
                "XX  X\n" +
                "X X X\n" +
                "X  XX\n" +
                "X   X\n";

        final var result = new LogicalImageDecoder().parseFromString(5, 5, input);

        Assert.assertEquals("X   X|", result.rowToString(1));
        Assert.assertEquals("XX  X|", result.rowToString(2));
        Assert.assertEquals("X X X|", result.rowToString(3));
        Assert.assertEquals("X  XX|", result.rowToString(4));
        Assert.assertEquals("X   X|", result.rowToString(5));
        Assert.assertEquals("XXXXX|", result.columnToString(1));
        Assert.assertEquals(" X   |", result.columnToString(2));
        Assert.assertEquals("  X  |", result.columnToString(3));
        Assert.assertEquals("   X |", result.columnToString(4));
        Assert.assertEquals("XXXXX|", result.columnToString(5));
    }
}
