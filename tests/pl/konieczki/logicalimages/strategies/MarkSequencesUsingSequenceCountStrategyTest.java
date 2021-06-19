package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class MarkSequencesUsingSequenceCountStrategyTest extends AbstractTestClassForRowStrategies {

    public MarkSequencesUsingSequenceCountStrategyTest() {
        super(new MarkSequencesUsingSequenceCountStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void oznaczPoLiczebnosciBrakCiagow() {
        baseFalseTest("???|");
    }

    @Test
    public void oznaczPoLiczebnosciJedenCiagNiekompletny() {
        baseTrueTest("?X?X???|3[-1:-1]", " XXX   |3[2:4*]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPoLiczebnosciJedenCiagKompletny() {
        baseFalseTest("?XXX???|3[2:4*]", new FieldSequence(1, 3, 2, 4));
    }

    @Test(expected = IllegalStateException.class)
    public void oznaczPoLiczebnosciJedenCiagZaDlugi() {
        final Game game = new Game(1, 7);
        final FieldsSequences fieldsSequences = new FieldsSequences(3);
        game.setHorizontalSequences(1, fieldsSequences);
        game.setField(1, 2, FieldState.FULL);
        game.setField(1, 6, FieldState.FULL);
        this.strategia.find(game);
    }

    @Test
    public void test1() {
        baseFalseTest("???????XX??????|4[-1:-1],2[-1:-1]", new FieldSequence(1, 4), new FieldSequence(2, 2));
    }

    @Test
    public void test2() {
        baseFalseTest("???????XXXX????|4[-1:-1],2[-1:-1]", new FieldSequence(1, 4), new FieldSequence(2, 2));
    }

    @Test
    public void test3() {
        baseTrueTest("????????X???X???????????|13[-1:-1]", "????????XXXXX???????????|13[9:13]", new FieldSequence(1, 13));
    }

    @Test
    public void test4() {
        baseTrueTest("????????X????????????????|9[-1:-1],2[-1:-1]", "????????X????????????????|9[9:9],2[-1:-1]",
                new FieldSequence(1, 9), new FieldSequence(2, 2)
        );
    }

    @Test
    public void test5() {
        baseTrueTest("????????????????X????????|2[-1:-1],9[-1:-1]", "????????????????X????????|2[-1:-1],9[17:17]",
                new FieldSequence(1, 2), new FieldSequence(2, 9)
        );
    }

    @Test
    public void test6() {
        baseFalseTest("  ???????????????X??    ?|3[-1:-1],1[-1:-1]", new FieldSequence(1, 3), new FieldSequence(2, 1));
    }
}
