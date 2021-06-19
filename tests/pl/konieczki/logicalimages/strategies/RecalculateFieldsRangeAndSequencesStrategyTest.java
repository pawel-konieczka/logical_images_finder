package pl.konieczki.logicalimages.strategies;

import org.junit.Assert;
import org.junit.Test;
import pl.konieczki.logicalimages.model.*;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class RecalculateFieldsRangeAndSequencesStrategyTest {

    @Test
    public void inicjalizacjaWierszaBezCiagow() {
        final Game game = new Game(1, 5);
        final RowsGameFieldTranslator translator = new RowsGameFieldTranslator(1);
        final FindResultStrategy strategy = new RecalculateFieldsRangeAndSequencesStrategy(translator);
        final boolean b = strategy.find(game);
        Assert.assertFalse(b);
        Assert.assertEquals(1, translator.getFieldsRangeStart(game));
        Assert.assertEquals(5, translator.getFieldsRangeEnd(game));
        Assert.assertTrue(translator.checkSequencesExistence(game));
    }

    @Test
    public void inicjalizacjaWierszaZJednymCiagiem() {
        final Game game = new Game(1, 5);
        game.setHorizontalSequences(1, new FieldsSequences(new FieldSequence(1, 2)));
        final RowsGameFieldTranslator translator = new RowsGameFieldTranslator(1);
        final FindResultStrategy strategy = new RecalculateFieldsRangeAndSequencesStrategy(translator);
        final boolean b = strategy.find(game);
        Assert.assertFalse(b);
        Assert.assertEquals(1, translator.getFieldsRangeStart(game));
        Assert.assertEquals(5, translator.getFieldsRangeEnd(game));
        Assert.assertFalse(translator.checkSequencesExistence(game));
        Assert.assertEquals(1, translator.getSequences(game).getSequence(0).getId());
    }

    @Test
    public void zawezenieZakresuPol() {
        // arrange
        final Game game = new Game(1, 5);
        final RowsGameFieldTranslator translator = new RowsGameFieldTranslator(1);
        final FindResultStrategy strategy = new RecalculateFieldsRangeAndSequencesStrategy(translator);
        strategy.find(game);
        Assert.assertEquals(1, translator.getFieldsRangeStart(game));
        Assert.assertEquals(5, translator.getFieldsRangeEnd(game));
        game.setField(1, 1, FieldState.FULL);
        game.setField(1, 5, FieldState.EMPTY);
        // act
        final boolean b = strategy.find(game);
        // assert
        Assert.assertTrue(b);
        Assert.assertEquals(1, translator.getFieldsRangeStart(game));
        Assert.assertEquals(4, translator.getFieldsRangeEnd(game));
    }

    @Test
    public void zawezenieCiagowPol() {
        // arrange
        final Game game = new Game(1, 15);
        final FieldSequence ciag1 = new FieldSequence(1, 1);
        final FieldSequence ciag2 = new FieldSequence(2, 1);
        final FieldSequence ciag3 = new FieldSequence(3, 1);
        game.setHorizontalSequences(1, new FieldsSequences(ciag1, ciag2, ciag3));
        final RowsGameFieldTranslator translator = new RowsGameFieldTranslator(1);
        final FindResultStrategy strategy = new RecalculateFieldsRangeAndSequencesStrategy(translator);
        strategy.find(game);
        Assert.assertEquals(3, translator.getSequences(game).getCount());
        ciag1.setFieldFrom(1);
        ciag1.setFieldTo(1);
        ciag3.setFieldFrom(15);
        ciag3.setFieldTo(15);
        game.setField(1, 1, FieldState.FULL);
        game.setField(1, 2, FieldState.EMPTY);
        game.setField(1, 14, FieldState.EMPTY);
        game.setField(1, 15, FieldState.FULL);
        // act
        final boolean b = strategy.find(game);
        // assert
        Assert.assertTrue(b);
        final FieldsSequences fieldsSequences = translator.getSequences(game);
        Assert.assertEquals(1, fieldsSequences.getCount());
        Assert.assertEquals(2, fieldsSequences.getSequence(0).getId());
    }

    @Test
    public void poPrzeliczeniuBrakZakresuPol() {
        final Game game = new Game(1, 5);
        game.setHorizontalSequences(1, new FieldsSequences(2));
        final RowsGameFieldTranslator translator = new RowsGameFieldTranslator(1);
        final FindResultStrategy strategy = new RecalculateFieldsRangeAndSequencesStrategy(translator);
        strategy.find(game);
        Assert.assertEquals(1, translator.getFieldsRangeStart(game));
        Assert.assertEquals(5, translator.getFieldsRangeEnd(game));
        game.setField(1, 1, FieldState.FULL);
        final boolean bStep1 = strategy.find(game);
        Assert.assertFalse(bStep1);
        Assert.assertEquals(1, translator.getFieldsRangeStart(game));
        Assert.assertEquals(5, translator.getFieldsRangeEnd(game));
        game.setAllFieldsInRangeTo(1, 1, 2, 5, FieldState.FULL);
        // act
        final boolean bTest = strategy.find(game);
        // assert
        Assert.assertFalse(bTest);
        Assert.assertEquals(1, translator.getFieldsRangeStart(game));
        Assert.assertEquals(5, translator.getFieldsRangeEnd(game));
    }

    @Test
    public void poPrzeliczeniuBrakCiagowPol() {
        final Game game = new Game(1, 5);
        final FieldSequence ciag1 = new FieldSequence(1, 2);
        final FieldSequence ciag2 = new FieldSequence(2, 2);
        game.setHorizontalSequences(1, new FieldsSequences(ciag1, ciag2));
        final RowsGameFieldTranslator translator = new RowsGameFieldTranslator(1);
        final FindResultStrategy strategy = new RecalculateFieldsRangeAndSequencesStrategy(translator);
        strategy.find(game);
        final FieldsSequences fieldsSequencesInit = translator.getSequences(game);
        Assert.assertEquals(2, fieldsSequencesInit.getCount());
        Assert.assertEquals(ciag1, fieldsSequencesInit.getSequence(0));
        Assert.assertEquals(ciag2, fieldsSequencesInit.getSequence(1));
        ciag1.setFieldFrom(1);
        ciag1.setFieldTo(2);
        ciag2.setFieldFrom(4);
        ciag2.setFieldTo(5);
        // act
        final boolean bTest = strategy.find(game);
        // assert
        Assert.assertFalse(bTest);
        final FieldsSequences fieldsSequencesTest = translator.getSequences(game);
        Assert.assertFalse(fieldsSequencesTest.isEmpty());
    }

    @Test
    public void nieZmieniajZakresuMimoCiaguKompletnegoBoNieMaPustegoPolaPoNim() {
        final Game game = new Game(1, 5);
        final FieldSequence cp1 = new FieldSequence(1, 2, 1, 2);
        final FieldSequence cp2 = new FieldSequence(2, 1);
        game.setHorizontalSequences(1, new FieldsSequences(cp1, cp2));
        game.setField(1, 1, FieldState.FULL);
        game.setField(1, 2, FieldState.FULL);
        // "XX???"|2[1,2*],1[-1,-1]
        final RowsGameFieldTranslator translator = new RowsGameFieldTranslator(1);
        final RecalculateFieldsRangeAndSequencesStrategy strategy = new RecalculateFieldsRangeAndSequencesStrategy(translator);
        final boolean b = strategy.find(game);
        Assert.assertFalse(b);
        final FieldsRange fieldsRange = translator.getFieldsRange(game);
        Assert.assertEquals(1, fieldsRange.getFieldFrom());
        Assert.assertEquals(5, fieldsRange.getFieldTo());
        final FieldsSequences fieldsSequences = translator.getSequences(game);
        Assert.assertEquals(2, fieldsSequences.getCount());
        final FieldSequence fieldSequence1 = fieldsSequences.getSequence(0);
        Assert.assertTrue(fieldSequence1.isCompleted());
        Assert.assertEquals(1, fieldSequence1.getFieldFrom());
        Assert.assertEquals(2, fieldSequence1.getFieldTo());
        final FieldSequence fieldSequence2 = fieldsSequences.getSequence(1);
        Assert.assertFalse(fieldSequence2.isCompleted());
    }
}
