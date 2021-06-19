package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class MarkAsEmptyUndefinedFieldsBetweenCompletedSequencesStrategyTest extends AbstractTestClassForRowStrategies {

    public MarkAsEmptyUndefinedFieldsBetweenCompletedSequencesStrategyTest() {
        super(new MarkAsEmptyUndefinedFieldsBetweenCompletedSequencesStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void singleRowWithTwoWholeSelectedFields() {
        baseTrueTest("XXX ?? XXX|3[1:3*],3[8:10*]", "XXX    XXX|3[1:3*],3[8:10*]",
                new FieldSequence(1, 3, 1, 3), new FieldSequence(2, 3, 8, 10));
    }

    @Test
    public void singleRowWithOneFullSelectedField() {
        baseTrueTest("XXXX ?|4[1:4*]", "XXXX  |4[1:4*]", new FieldSequence(1, 4, 1, 4));
    }

    @Test
    public void singleRowWithTwoFullSelectedField() {
        baseTrueTest("????? XXXX|4[7:10*]", "      XXXX|4[7:10*]", new FieldSequence(1, 4, 7, 10));
    }

    @Test
    public void trzyCiagiCzteryPrzedzialy() {
        baseTrueTest("? XX ?? XX ?? XX ?|2[3:4*],2[9:10*],2[15:16*]",
                "  XX    XX    XX  |2[3:4*],2[9:10*],2[15:16*]",
                new FieldSequence(1, 2, 3, 4), new FieldSequence(2, 2, 9, 10),
                new FieldSequence(3, 2, 15, 16)
        );
    }
}
