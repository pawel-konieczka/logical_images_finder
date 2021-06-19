package pl.konieczki.logicalimages.strategies;

import org.junit.Assert;
import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class MarkCommonFieldsStrategyTest extends AbstractTestClassForRowStrategies {

    public MarkCommonFieldsStrategyTest() {
        super(new MarkCommonFieldsStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void brakWspolnych() {
        baseFalseTest("??????????|5[-1:-1]", new FieldSequence(1, 5));
    }

    @Test
    public void jedenWspolny() {
        baseTrueTest("??????????|8[-1:-1]", "??XXXXXX??|8[3:8]", new FieldSequence(1, 8));
    }

    @Test
    public void calyWspolny() {
        baseTrueTest("??????????|10[-1:-1]", "XXXXXXXXXX|10[1:10*]", new FieldSequence(1, 10));
    }

    @Test
    public void dwaWspoleOdLewejIOdPrawej() {
        baseTrueTest("?????????????????????????|19[-1:-1],3[-1:-1]",
                "??XXXXXXXXXXXXXXXXX???X??|19[3:19],3[23:23]",
                new FieldSequence(1, 19), new FieldSequence(2, 3));
    }

    @Test
    public void trzyWspole() {
        baseTrueTest("???????????????|4[-1:-1],4[-1:-1],4[-1:-1]",
                "?XXX??XXX??XXX?|4[2:4],4[7:9],4[12:14]",
                new FieldSequence(1, 4), new FieldSequence(2, 4), new FieldSequence(3, 4));
    }

    @Test
    public void zaczynaszNieOdPoczatkuZJednymKompletnymNaPoczatku() {
        baseTrueTest(
                "XX ????? |2[1:2*],4[-1:-1]", "XX ?XXX? |2[1:2*],4[5:7]",
                new FieldSequence(1, 2, 1, 2), new FieldSequence(2, 4)
        );
    }

    @Test
    public void zaczynaszNieOdPoczatkuZJednymKompletnymNaKoncu() {
        baseTrueTest(
                " ????? XX|4[-1:-1],2[8:9*]", " ?XXX? XX|4[3:5],2[8:9*]",
                new FieldSequence(1, 4), new FieldSequence(2, 2, 8, 9)
        );
    }

    @Test
    public void zPrzeliczeniemCiagowIPol() {
        baseTrueTest(" XX ????? X  |2[2:3*],4[-1:-1],1[11:11*]", " XX ?XXX? X  |2[2:3*],4[6:8],1[11:11*]",
                true, new FieldSequence(1, 2, 2, 3), new FieldSequence(2, 4), new FieldSequence(3, 1, 11, 11));
    }

    @Test
    public void zPrzeliczeniemCiagowIPol2() {
        // "X ????????? X"
        // "X ?xx??xxx? X"
        final Game p = new Game(1, 13);
        final FieldsSequences cp = new FieldsSequences(
                new FieldSequence(1, 1, 1, 1),
                new FieldSequence(2, 3),
                new FieldSequence(3, 4),
                new FieldSequence(4, 1, 13, 13)
        );
        p.setHorizontalSequences(1, cp);
        p.setField(1, 1, FieldState.FULL);
        p.setField(1, 2, FieldState.EMPTY);
        p.setField(1, 12, FieldState.EMPTY);
        p.setField(1, 13, FieldState.FULL);

        final RowsGameFieldTranslator translator = new RowsGameFieldTranslator(1);
        translator.recalculateFieldsRangeAndSequences(p);
        Assert.assertEquals(3, translator.getFieldsRangeStart(p));
        Assert.assertEquals(11, translator.getFieldsRangeEnd(p));
        final FieldsSequences fieldsSequences = translator.getSequences(p);
        Assert.assertEquals(2, fieldsSequences.getCount());
        Assert.assertEquals(2, fieldsSequences.getSequence(0).getId());
        Assert.assertEquals(3, fieldsSequences.getSequence(1).getId());
        final MarkCommonFieldsStrategy strategy = new MarkCommonFieldsStrategy(translator);
        final boolean b = strategy.find(p);
        Assert.assertTrue(b);
        Assert.assertEquals("X ?XX??XXX? X|1[1:1*],3[4:5],4[8:10],1[13:13*]", p.rowToString(1));
    }
}