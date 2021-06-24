package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.strategies.sequences.MarkFirstSequenceIfFirstFullFieldBlocksSequenceStrategy;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class MarkFirstSequenceIfFirstFullFieldBlocksSequenceStrategyTest extends AbstractTestClassForRowStrategies {

    public MarkFirstSequenceIfFirstFullFieldBlocksSequenceStrategyTest() {
        super(new MarkFirstSequenceIfFirstFullFieldBlocksSequenceStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void oznaczSkrajnyCiagGdyDlugoscNiePozwalaZmiescicInnego() {
        baseTrueTest(
                "?X?????????XXX??????|1[-1:-1],9[12:14]", "?X?????????XXX??????|1[2:2*],9[12:14]",
                new FieldSequence(1, 1), new FieldSequence(2, 9, 12, 14)
        );
    }
}