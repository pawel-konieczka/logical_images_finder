package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.strategies.sequences.ExpandSequencesRangeForFullFieldsStrategy;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class ExpandSequencesRangeForFullFieldsStrategyTest extends AbstractTestClassForRowStrategies {

    public ExpandSequencesRangeForFullFieldsStrategyTest() {
        super(new ExpandSequencesRangeForFullFieldsStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void rozszerzGdyUstaloneSameNieokresloneBezZmian() {
        baseFalseTest("?????|3[-1:-1]", new FieldSequence(1, 3));
    }

    @Test
    public void rozszerzGdyUstaloneSamePelneBezZmian() {
        baseFalseTest("XXXXX|5[1:5*]", new FieldSequence(1, 5, 1, 5));
    }

    @Test
    public void rozszerzGdyUstaloneSamePelneLubPusteBezZmian() {
        baseFalseTest(" XXX |3[2:4*]", new FieldSequence(1, 3, 2, 4));
    }

    @Test
    public void rozszerzGdyUstaloneSamePelne() {
        baseTrueTest("XXXXX|5[3:3]", "XXXXX|5[1:5*]", new FieldSequence(1, 5, 3, 3));
    }

    @Test
    public void rozszerzGdyUstaloneSamePelneLubPuste() {
        baseTrueTest(" XXX |3[4:4]", " XXX |3[2:4*]", new FieldSequence(1, 3, 4, 4));
    }
}