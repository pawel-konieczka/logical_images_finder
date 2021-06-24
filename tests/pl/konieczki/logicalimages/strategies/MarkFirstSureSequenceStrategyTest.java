package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.strategies.sequences.MarkFirstSureSequenceStrategy;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class MarkFirstSureSequenceStrategyTest extends AbstractTestClassForRowStrategies {

    public MarkFirstSureSequenceStrategyTest() {
        super(new MarkFirstSureSequenceStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void oznaczPierwszyCiagOdPoczatkuOdBrzegu() {
        baseTrueTest("X????|3[-1:-1]", "XXX??|3[1:3*]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPierwszyCiagOdPoczatkuPoPustych() {
        baseTrueTest("  X????|3[-1:-1]", "  XXX??|3[3:5*]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPierwszyCiagOdPoczatkuPoNieokreslonychBrakZmian() {
        baseFalseTest("??X????|3[-1:-1]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczDrugiCiagOdPoczatkuPoPierwszymKompletnymOznaczonym() {
        baseTrueTest("XX  X?????|2[1:2*],3[5:5]", "XX  XXX???|2[1:2*],3[5:7*]",
                new FieldSequence(1, 2, 1, 2), new FieldSequence(2, 3, 5, 5));
    }

    @Test
    public void oznaczPierwszyCiagOdKoncaOdBrzegu() {
        baseTrueTest("????X|3[-1:-1]", "??XXX|3[3:5*]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPierwszyCiagOdKoncaPoPustych() {
        baseTrueTest("????X  |3[-1:-1]", "??XXX  |3[3:5*]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczPierwszyCiagOdKoncaPoNieokreslonychBrakZmian() {
        baseFalseTest("????X??|3[-1:-1]", new FieldSequence(1, 3));
    }

    @Test
    public void oznaczDrugiCiagOdKoncaPoKompletnymOznaczonym() {
        baseTrueTest("?????X  XX|3[6:6],2[9:10*]", "???XXX  XX|3[4:6*],2[9:10*]",
                new FieldSequence(1, 3, 6, 6), new FieldSequence(2, 2, 9, 10));
    }

    @Test
    public void blad20210416() {
        baseTrueTest(" ?XXXXXXXXXXXXXXXXXX  XXX|19[3:20],3[23:24]", " ?XXXXXXXXXXXXXXXXXX  XXX|19[3:20],3[23:25*]",
                new FieldSequence(1, 19, 3,20), new FieldSequence(2, 3, 23, 24)
        );
    }
}