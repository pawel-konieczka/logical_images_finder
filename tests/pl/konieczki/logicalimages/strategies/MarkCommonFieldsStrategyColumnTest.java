package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.translator.ColumnsGameFieldTranslator;

public class MarkCommonFieldsStrategyColumnTest extends AbstractTestClassForColStrategies {

    public MarkCommonFieldsStrategyColumnTest() {
        super(new MarkCommonFieldsStrategy(new ColumnsGameFieldTranslator(1)));
    }

    @Test
    public void commonFieldsInCol() {
        baseTrueTest("??????????|4,4", "?XXX??XXX?|4,4", new FieldSequence(1, 4), new FieldSequence(2, 4));
    }
}
