package pl.konieczki.logicalimages.strategies.sequences;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.*;
import pl.konieczki.logicalimages.strategies.AbstractStrategy;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategia, w zależności od liczby ciągów w wierszu/kolumnie, stara się przypisać wykryte pełne pole
 * do właściwego ciągu.
 * Dla liczebności równej jeden, znajduje pierwsze od początku pole pełne oraz pierwsze od końca pole pełne.
 * Wszystkie wewnątrz pola oznacza jako pełne. Jeśli zakres jest kompletny, oznacza wszystkie przed pierwszym polem
 * i wszystkie za pierwszym polem jako puste.
 * Dla liczebności równej dwa, @TODO
 * Dla liczebności większej niż dwa, wyznacza przedziały ze zmianami stanów ({@link FieldState}).
 * Jeśli od początku jest zmiana z {@link FieldState#UNDEFINED} na {@link FieldState#FULL} i długość pierwszego podziału
 * jest mniejsza niż długość pierwszego ciągu, to oznaczamy pole pełne jako przynależne do pierwszego ciągu.
 * Analogicznie dla podziałów i ciągów od końca.
 * Strategia pól i ciągów.
 */
public class MarkSequencesUsingSequenceCountStrategy extends AbstractStrategy {

    public MarkSequencesUsingSequenceCountStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean internalFind(@NonNull Game game) {
        final var sequences = translator.getSequences(game);
        if (sequences.isEmpty())
            return markNoMoreChangesPossibility();
        if (sequences.getCount() == 1) {
            return oznaczZakresDlaPojedynczego(sequences, game);
        }
        if (sequences.getCount() == 2) {
            return oznaczZakresDlaPodwojnego(sequences, game);
        }
        return oznaczZakresDlaWielokrotnych(sequences, game);
    }

    @Override
    protected boolean checkIfAllFieldsProperlyMarked(@NonNull Game game) {
        return translator.getSequences(game).checkIfAllSequencesAreCompleted();
    }

    private boolean oznaczZakresDlaWielokrotnych(FieldsSequences sequences, Game game) {
        assert sequences.getCount() > 2;
        final var firstSequence = sequences.getFirst();
        final var lastSequence = sequences.getLast();
        final var divisions = findDivisions(game);
        if (oznaczZakresBrzegowyOdPoczatku(divisions, firstSequence))
            return expandFieldsRange(firstSequence, divisions[1]);
        if (oznaczZakresBrzegowyOdKonca(divisions, lastSequence))
            return expandFieldsRange(lastSequence, divisions[divisions.length - 2]);
        return false;
    }

    private boolean oznaczZakresDlaPodwojnego(FieldsSequences sequences, Game game) {
        assert sequences.getCount() == 2;
        final var firstSequence = sequences.getFirst();
        final var lastSequence = sequences.getLast();
        // ???????????????|3,9 a
        // X??????????????|3,9 b
        // ?X?????????????|3,9 c
        // ??????????XXXXX|3,9 d
        // ??????????XXXX?|3,9 e
        // ?XX???XXXXXXX??|3,9 f
        // ??????X?XXXXX??|3,9 g ???
        // ?XX???XXXXX????|3,9 h
        // ?X??? XXXX?????|3,4 @TODO
        final FieldsRangeWithState[] divisions = findDivisions(game);
        if (oznaczZakresDlaPodwojnegoCzyWariantA(divisions)) { // a
            return false;
        }
        if (oznaczZakresDlaPodwojnegoCzyWariantB(divisions)) { // b
            return expandFieldsRange(firstSequence, divisions[0]);
        }
        if (oznaczZakresDlaPodwojnegoCzyWariantD(divisions)) { // d
            return expandFieldsRange(lastSequence, divisions[1]);
        }
        if (oznaczZakresDlaPodwojnegoCzyWariantC(divisions, firstSequence)) { // c
            return expandFieldsRange(firstSequence, divisions[1]);
        }
        if (oznaczZakresDlaPodwojnegoCzyWariantE(divisions, lastSequence)) { // e
            return expandFieldsRange(lastSequence, divisions[1]);
        }
        if (oznaczZakresDlaPodwojnegoCzyWariantF(divisions, firstSequence)) { // f
            return expandFieldsRange(firstSequence, divisions[1]);
        }
        if (oznaczZakresDlaPodwojnegoCzyWariantH(divisions, lastSequence)) { // h
            return expandFieldsRange(lastSequence, divisions[3]);
        }
        return false;
    }

    private boolean oznaczZakresDlaPodwojnegoCzyWariantA(FieldsRangeWithState[] divisions) {
        return divisions.length == 1 && divisions[0].getState() == FieldState.UNDEFINED;
    }

    private boolean oznaczZakresDlaPodwojnegoCzyWariantB(FieldsRangeWithState[] divisions) {
        return divisions.length == 2 && divisions[0].getState() == FieldState.FULL;
    }

    private boolean oznaczZakresDlaPodwojnegoCzyWariantD(FieldsRangeWithState[] divisions) {
        return divisions.length == 2 && divisions[1].getState() == FieldState.FULL;
    }

    private boolean oznaczZakresDlaPodwojnegoCzyWariantC(FieldsRangeWithState[] divisions, FieldSequence firstSequence) {
        // ?X?????????????|3,9
        // ????????X????????????????|9[-1:-1],2[-1:-1]
        return divisions.length == 3 && oznaczZakresBrzegowyOdPoczatku(divisions, firstSequence);
    }

    private boolean oznaczZakresDlaPodwojnegoCzyWariantE(FieldsRangeWithState[] divisions, FieldSequence lastSequence) {
        // ??????????XXXX?|3,9
        // ????????????????X????????|2[-1:-1],9[-1:-1]
        return divisions.length == 3 && oznaczZakresBrzegowyOdKonca(divisions, lastSequence);
    }

    private boolean oznaczZakresDlaPodwojnegoCzyWariantF(FieldsRangeWithState[] divisions, FieldSequence firstSequence) {
        // ?XX???XXXXXXX??|3,9 f
        return divisions.length == 5 && oznaczZakresBrzegowyOdPoczatku(divisions, firstSequence);
    }

    private boolean oznaczZakresDlaPodwojnegoCzyWariantH(FieldsRangeWithState[] divisions, FieldSequence lastSequence) {
        return divisions.length == 5 && oznaczZakresBrzegowyOdKonca(divisions, lastSequence);
    }

    private boolean oznaczZakresBrzegowyOdPoczatku(FieldsRangeWithState[] divisions, FieldSequence firstSequence) {
        return divisions.length > 1
                && divisions[0].getState() == FieldState.UNDEFINED
                && divisions[1].getState() == FieldState.FULL
                && firstSequence.getLength() > divisions[0].getLength();
    }

    private boolean oznaczZakresBrzegowyOdKonca(FieldsRangeWithState[] divisions, FieldSequence lastSequence) {
        return divisions.length > 1
                && divisions[divisions.length - 1].getState() == FieldState.UNDEFINED
                && divisions[divisions.length - 2].getState() == FieldState.FULL
                && lastSequence.getLength() > divisions[divisions.length - 1].getLength();
    }

    private boolean expandFieldsRange(FieldSequence sequence, FieldsRange fieldsRange) {
        if (!sequence.isIdentified())
            return sequence.setRange(fieldsRange);
        final boolean b1 = sequence.getFieldFrom() > fieldsRange.getFieldFrom();
        final boolean b2 = sequence.getFieldTo() < fieldsRange.getFieldTo();
        if (b1)
            sequence.setFieldFrom(fieldsRange.getFieldFrom());
        if (b2)
            sequence.setFieldTo(fieldsRange.getFieldTo());
        return b1 || b2;
    }

    private FieldsRangeWithState[] findDivisions(Game game) {
        final List<FieldsRangeWithState> divisions = new ArrayList<>();
        final var fieldsRange = translator.getFieldsRange(game);
        var poprzedniOd = fieldsRange.getFieldFrom();
        var poprzedni = translator.getFieldStateInRange(game, poprzedniOd);
        for (int p = poprzedniOd + 1; p <= fieldsRange.getFieldTo(); p++) {
            final var pole = translator.getFieldStateInRange(game, p);
            if (pole != poprzedni) {
                divisions.add(new FieldsRangeWithState(poprzedniOd, p - 1, poprzedni));
                poprzedniOd = p;
                poprzedni = pole;
            }
        }
        divisions.add(new FieldsRangeWithState(poprzedniOd, fieldsRange.getFieldTo(), poprzedni));
        return divisions.toArray(new FieldsRangeWithState[0]);
    }

    private boolean oznaczZakresDlaPojedynczego(FieldsSequences sequences, Game game) {
        assert sequences.getCount() == 1;
        final var firstSequence = sequences.getFirst();
        if (firstSequence.isCompleted())
            return markNoMoreChangesPossibility();
        final var fieldsRange = translator.getFieldsRange(game);
        final var firstFullFieldIdx =
                translator.findFirstFieldInRangeWithStateFromBeginning(fieldsRange, game, FieldState.FULL);
        if (firstFullFieldIdx == FieldsRange.UNDEFINED)
            return false; // nie znaleziono żadnego
        final int lastFullFieldIdx =
                translator.findFirstFieldInRangeWithStateFromEnding(fieldsRange, game, FieldState.FULL);
        final boolean b1 = firstSequence.setFieldFrom(firstFullFieldIdx);
        final boolean b2 = firstSequence.setFieldTo(lastFullFieldIdx);
        final boolean b3 = firstSequence.isCompleted()
                && translator.setAllFieldsInRangeTo(game, fieldsRange.getFieldFrom(), firstFullFieldIdx - 1, FieldState.EMPTY);
        final boolean b4 = translator.setAllFieldsInRangeTo(game, firstFullFieldIdx, lastFullFieldIdx, FieldState.FULL);
        final boolean b5 = firstSequence.isCompleted()
                && translator.setAllFieldsInRangeTo(game, lastFullFieldIdx + 1, fieldsRange.getFieldTo(), FieldState.EMPTY);
        return b1 || b2 || b3 || b4 || b5;
    }
}