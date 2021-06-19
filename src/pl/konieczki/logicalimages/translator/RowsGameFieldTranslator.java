package pl.konieczki.logicalimages.translator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsRange;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

@RequiredArgsConstructor
public class RowsGameFieldTranslator extends AbstractGameFieldTranslator {

    private final int rowIdx;

    @Override
    public boolean checkIfAllFieldsMarkedInRange(@NonNull Game game) {
        return game.checkIfAllFieldsMarkedInRange(this.rowIdx, this.rowIdx, 1, game.getColCount());
    }

    @Override
    public boolean setAllFieldsInRangeTo(@NonNull Game game, int odPola, int doPola, @NonNull FieldState stan) {
        return game.setAllFieldsInRangeTo(this.rowIdx, this.rowIdx, odPola, doPola, stan);
    }

    @Override
    public FieldState getFieldStateInRange(@NonNull Game game, int indeksPola) {
        return game.getField(this.rowIdx, indeksPola);
    }

    @Override
    public boolean setFieldStateInRange(@NonNull Game game, int indeksPola, @NonNull FieldState fieldState) {
        return game.setField(this.rowIdx, indeksPola, fieldState);
    }

    @Override
    public FieldsRange getDefinedFieldsRange(@NonNull Game game) {
        return game.getColCount() > 0
                ? FieldsRange.createRange(1, game.getColCount())
                : FieldsRange.createUndefined();
    }

    @Override
    public FieldsSequences getDefinedSequences(@NonNull Game game) {
        return game.getHorizontalSequencesAt(this.rowIdx);
    }

    @Override
    public String toString() {
        return "translator wiersza " + this.rowIdx;
    }
}