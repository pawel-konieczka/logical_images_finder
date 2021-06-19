package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class MarkAsEmptyWhenNoSequencesStrategyTest extends AbstractTestClassForRowStrategies {

    public MarkAsEmptyWhenNoSequencesStrategyTest() {
        super(new MarkAsEmptyWhenNoSequencesStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void gameHasEmptyRow() {
        baseTrueTest("????|", "    |");
    }

    @Test
    public void gameHasNotEmptyRow() {
        baseFalseTest("????|2[-1:-1]", new FieldSequence(1, 2));
    }
}
