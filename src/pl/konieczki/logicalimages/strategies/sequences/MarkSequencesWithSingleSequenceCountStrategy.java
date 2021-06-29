package pl.konieczki.logicalimages.strategies.sequences;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsRange;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Strategia, w zależności od liczby ciągów w wierszu/kolumnie, stara się przypisać wykryte pełne pole
 * do właściwego ciągu.
 * Dla liczebności równej jeden, znajduje pierwsze od początku pole pełne oraz pierwsze od końca pole pełne.
 * Wszystkie wewnątrz pola oznacza jako pełne. Jeśli zakres jest kompletny, oznacza wszystkie przed pierwszym polem
 * i wszystkie za pierwszym polem jako puste.
 * Strategia pól i ciągów.
 */
public class MarkSequencesWithSingleSequenceCountStrategy extends AbstractSequencesStrategy {

    public MarkSequencesWithSingleSequenceCountStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean checkIfStrategyIsApplicable(@NonNull Game game) {
        return !translator.getSequences(game).checkIfAllSequencesAreIdentified();
    }

    @Override
    protected boolean internalFind(@NonNull Game game) {
        final var sequences = translator.getSequences(game);
        if (sequences.getCount() > 1) // do not process if there's more sequences then one in range
            return false;
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