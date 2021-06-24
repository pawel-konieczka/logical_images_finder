package pl.konieczki.logicalimages.strategies;

import org.junit.Test;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.strategies.fields.SetAsEmptyUnreachableFieldsStrategy;
import pl.konieczki.logicalimages.translator.RowsGameFieldTranslator;

public class SetAsEmptyUnreachableFieldsStrategyTest extends AbstractTestClassForRowStrategies {

    public SetAsEmptyUnreachableFieldsStrategyTest() {
        super(new SetAsEmptyUnreachableFieldsStrategy(new RowsGameFieldTranslator(1)));
    }

    @Test
    public void piecKolumnTrzyZTrzechBrakZmiany() {
        baseFalseTest(" XXX |3[2:4*]", new FieldSequence(1, 3, 2, 4));
    }

    @Test
    public void piecKolumnTrzyZTrzechOznaczenieBrzegowych() {
        baseTrueTest("?XXX?|3[2:4*]", " XXX |3[2:4*]", new FieldSequence(1, 3, 2, 4));
    }

    @Test
    public void piecKolumnTrzyZCzterechBrakNieosiagalnych() {
        baseFalseTest("?XXX?|4[2:4]", new FieldSequence(1, 4, 2, 4));
    }

    @Test
    public void dziesiecKolumnCzteryZPieciu() {
        baseTrueTest("???XXXX???|5[4:7]", "  ?XXXX?  |5[4:7]", new FieldSequence(1, 5, 4, 7));
    }

    @Test
    public void piecKolumnDwaPierwszeZaznaczoneZCzterech() {
        baseTrueTest("XX???|4[1:2]", "XX?? |4[1:2]", new FieldSequence(1, 4, 1, 2));
    }

    @Test
    public void piecKolumnDwiePierwszeZaznaczoneZTrzech() {
        baseTrueTest("XX???|3[1:2]", "XX?  |3[1:2]", new FieldSequence(1, 3, 1, 2));
    }

    @Test
    public void trzyKolumnyOstatniaJednaZaznaczonaZDwoch() {
        baseTrueTest("??X|2[3:3]", " ?X|2[3:3]", new FieldSequence(1, 2, 3, 3));
    }

    @Test
    public void piecKolumnOstatnieDwieZaznaczoneZTrzech() {
        baseTrueTest("???XX|3[4:5]", "  ?XX|3[4:5]", new FieldSequence(1, 3, 4, 5));
    }

    @Test
    public void dwieKolumnyPierwszyCiagKompletnyDrugiCiagPiecZSiedmiu() {
        baseTrueTest("XXX ??XXXXX????|3[1:3*],7[7:11]", "XXX ??XXXXX??  |3[1:3*],7[7:11]",
                new FieldSequence(1, 3, 1, 3),
                new FieldSequence(2, 7, 7, 11)
        );
    }

    @Test
    public void siedemKolumnOdPoczatkuJedenZDwochOdKoncaJedenZTrzech() {
        baseTrueTest("X?????X|2[1:1],3[7:7]", "X?  ??X|2[1:1],3[7:7]",
                new FieldSequence(1, 2, 1, 1),
                new FieldSequence(2, 3, 7, 7)
        );
    }

    @Test
    public void pietnascieKolumnDwaCiagiCzteryIDwa() {
        baseTrueTest("????? XXXX??X  |4[7:10*],2[13:13]", "      XXXX ?X  |4[7:10*],2[13:13]",
                new FieldSequence(1, 4, 7, 10),
                new FieldSequence(2, 2, 13, 13)
        );
    }
}