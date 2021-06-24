package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.strategies.fields.SetAsEmptyUnreachableFieldsStrategy;
import pl.konieczki.logicalimages.translator.ColumnsGameFieldTranslator;

public class SetAsEmptyUnreachableFieldsStrategyColumnTest extends AbstractTestClassForColStrategies {

    public SetAsEmptyUnreachableFieldsStrategyColumnTest() {
        super(new SetAsEmptyUnreachableFieldsStrategy(new ColumnsGameFieldTranslator(1)));
    }

    @Test
    public void tenRowsOneColumnFifeShouldFourIsSelected() {
        baseTrueTest("????XXXX??|5", "   ?XXXX? |5", new FieldSequence(1, 5, 5, 8));
    }

    @Test
    public void sixRowsOneColumnFourShouldTwoIsSelected() {
        baseFalseTest("?XX??|4", new FieldSequence(1, 4, 2, 3));
    }

    @Test
    public void treeRowsOneColumnTwoShouldOneIsSelectedAtTheBeginning() {
        baseTrueTest("X??|2", "X? |2", new FieldSequence(1, 2, 1, 1));
    }

    @Test
    public void treeRowsOneColumnTwoShouldOneIsSelectedAtTheEnd() {
        baseTrueTest("??X|2", " ?X|2", new FieldSequence(1, 2, 3, 3));
    }
}
