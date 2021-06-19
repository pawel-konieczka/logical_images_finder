package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class MarkCompletedSequencesBordersStrategyTest extends AbstractTestClassForRowStrategies {

    public MarkCompletedSequencesBordersStrategyTest() {
        super(new MarkCompletedSequencesBordersStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void dwaKompletneCiagi() {
        baseTrueTest(
                "XXXX??XXX?|4[1:4*],3[7:9*]", "XXXX  XXX |4[1:4*],3[7:9*]",
                new FieldSequence(1, 4, 1, 4), new FieldSequence(2, 3, 7, 9)
        );
    }

    @Test
    public void dwaKompletneCiagiINieoznaczony() {
        baseTrueTest(
                "XXXX???XX?|4[1:4*],2[8:9*]", "XXXX ? XX |4[1:4*],2[8:9*]",
                new FieldSequence(1, 4, 1, 4), new FieldSequence(2, 2, 8, 9)
        );
    }

    @Test
    public void jedenKompletnyCiagOdLewej() {
        baseTrueTest("XXXX??|4[1:4*]", "XXXX ?|4[1:4*]", new FieldSequence(1, 4, 1, 4));
    }

    @Test
    public void jedenKompletnyCiagOdPrawej() {
        baseTrueTest("??XXXX|4[3:6*]", "? XXXX|4[3:6*]", new FieldSequence(1, 4, 3, 6));
    }

    @Test
    public void dwaKompletneCiagiNaBrzegach() {
        baseTrueTest("XXXX???XXX|4[1:4*],3[8:10*]", "XXXX ? XXX|4[1:4*],3[8:10*]",
                new FieldSequence(1, 4, 1, 4), new FieldSequence(2, 3, 8, 10)
        );
    }
}