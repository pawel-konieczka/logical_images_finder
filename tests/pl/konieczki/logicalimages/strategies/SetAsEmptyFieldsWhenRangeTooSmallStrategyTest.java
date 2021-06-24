package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.strategies.fields.SetAsEmptyFieldsWhenRangeTooSmallStrategy;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class SetAsEmptyFieldsWhenRangeTooSmallStrategyTest extends AbstractTestClassForRowStrategies {

    public SetAsEmptyFieldsWhenRangeTooSmallStrategyTest() {
        super(new SetAsEmptyFieldsWhenRangeTooSmallStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void nieMaNiezidentyfikowanychCiagow() {
        baseFalseTest("X ??? XXXX|1[1:1*],4[7:10*]",
                new FieldSequence(1, 1, 1, 1),
                new FieldSequence(2, 4, 7, 10)
        );
    }

    @Test
    public void jedenCiagDlugosciTrzy() {
        baseTrueTest("?XX? ??|3[2:3]", "?XX?   |3[2:3]", new FieldSequence(1, 3, 2, 3));
    }

    @Test
    public void dwaCiagiPoDwaJedenDoUsuniecia() {
        baseTrueTest("X?  ? X?|2[1:1],2[7:7]", "X?    X?|2[1:1],2[7:7]",
                new FieldSequence(1, 2, 1, 1), new FieldSequence(2, 2, 7, 7));
    }

    @Test
    public void dwaCiagiPoDwaBezZmian() {
        baseFalseTest("X? ?? ?X|2[1:1],2[8:8]",
                new FieldSequence(1, 2, 1, 1), new FieldSequence(2, 2, 8, 8));
    }

    @Test
    public void trzyCiagiPoJedenBezZmian() {
        baseFalseTest("X ?? ?X|1[1:1*],1[-1:-1],1[7:7*]",
                new FieldSequence(1, 1, 1, 1), new FieldSequence(2, 1),
                new FieldSequence(3, 1, 7, 7));
    }

    @Test
    public void dwaCiagiPoTrzyBezZmian() {
        baseFalseTest("X?? ??X|3[1:1],3[7:7]",
                new FieldSequence(1, 3, 1, 1), new FieldSequence(2, 3, 7, 7));
    }

    @Test
    public void dwaCiagiPoTrzyDwaDoUsuniecia() {
        baseTrueTest("X?X ?? ?X?|3[1:1],3[9:9]", "X?X    ?X?|3[1:1],3[9:9]",
                new FieldSequence(1, 3, 1, 1), new FieldSequence(2, 3, 9, 9));
    }

    @Test
    public void jedenCiagPoCzteryUsuwaDwa() {
        baseTrueTest(" ? ?? ??X?|4[9:9]", "      ??X?|4[9:9]", new FieldSequence(1, 4, 9, 9));
    }
}