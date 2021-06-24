package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.strategies.sequences.MarkFirstSequenceWhenFirstFullFieldIsInRangeOfSequenceStrategy;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class MarkFirstSequenceWhenFirstFullFieldIsInRangeOfSequenceStrategyTest extends AbstractTestClassForRowStrategies {

    public MarkFirstSequenceWhenFirstFullFieldIsInRangeOfSequenceStrategyTest() {
        super(new MarkFirstSequenceWhenFirstFullFieldIsInRangeOfSequenceStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void oznaczPierwszyCiagOdPoczatkuOdBrzegu() {
        baseTrueTest("X????|3[-1:-1]", "X????|3[1:1]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPierwszyCiagOdPoczatkuPoPustych() {
        baseTrueTest("  X????|3[-1:-1]", "  X????|3[3:3]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPierwszyCiagOdPoczatkuPoNieokreslonychBrakZmian() {
        baseTrueTest("??X????|3[-1:-1]", "??X????|3[3:3]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPierwszyCiagOdKoncaOdBrzegu() {
        baseTrueTest("????XXX|3[-1:-1]", "????XXX|3[5:7*]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPierwszyCiagOdPoczatkuIKonca() {
        baseTrueTest("??XXX|3[-1:-1]", "??XXX|3[3:5*]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPierwszyCiagOdKoncaPoPustych() {
        baseTrueTest("????X  |3[-1:-1]", "????X  |3[5:5]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPierwszyCiagOdKoncaPoNieokreslonychBrakZmian() {
        baseTrueTest("????X??|3[-1:-1]", "????X??|3[5:5]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczDrugiCiagOdKoncaGdyDwaPelneSaDlaDrugiegoCiagu() {
        baseTrueTest(
                "???????????X?X??????|1[-1:-1],9[14:14]", "???????????X?X??????|1[-1:-1],9[12:14]",
                new FieldSequence(1, 1), new FieldSequence(2, 9, 14, 14)
        );
    }
}