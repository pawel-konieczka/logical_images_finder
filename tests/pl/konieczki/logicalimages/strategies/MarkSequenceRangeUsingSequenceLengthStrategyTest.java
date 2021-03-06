package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.strategies.sequences.MarkSequenceRangeUsingSequenceLengthStrategy;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class MarkSequenceRangeUsingSequenceLengthStrategyTest extends AbstractTestClassForRowStrategies {

    public MarkSequenceRangeUsingSequenceLengthStrategyTest() {
        super(new MarkSequenceRangeUsingSequenceLengthStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void oznaczPierwszyIOstatniCiagGdyDlugoscCiaguJestWiekszaWersja1() {
        baseTrueTest(
                "??X???????X?X??????X??|4[3:3],3[-1:-1],4[20:20]", "??X???????X?X??????X??|4[3:4],3[-1:-1],4[19:20]",
                new FieldSequence(1, 4, 3, 3), new FieldSequence(2, 3),
                new FieldSequence(3, 4, 20, 20)
        );
    }

    @Test
    public void oznaczPierwszyIOstatniCiagGdyDlugoscCiaguJestWiekszaWersja2() {
        baseTrueTest(
                "?X????????????????????|4[2:2],2[-1:-1],4[-1:-1],4[-1:-1]", "?X????????????????????|4[2:4],2[-1:-1],4[-1:-1],4[-1:-1]",
                new FieldSequence(1, 4, 2, 2), new FieldSequence(2, 2),
                new FieldSequence(3, 4), new FieldSequence(4, 4)
        );
    }
}