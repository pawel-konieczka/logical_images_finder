package pl.konieczki.logicalimages.translator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsRange;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

@RequiredArgsConstructor
public class ColumnsGameFieldTranslator extends AbstractGameFieldTranslator {

    private final int columnIdx;

    @Override
    public boolean checkIfAllFieldsMarkedInRange(@NonNull Game game) {
        return game.checkIfAllFieldsMarkedInRange(1, game.getRowCount(), this.columnIdx, this.columnIdx);
    }

    @Override
    public boolean setAllFieldsInRangeTo(@NonNull Game game, int odPola, int doPola, @NonNull FieldState stan) {
        return game.setAllFieldsInRangeTo(odPola, doPola, this.columnIdx, this.columnIdx, stan);
    }

    @Override
    public FieldState getFieldStateInRange(@NonNull Game game, int indeksPola) {
        return game.getField(indeksPola, this.columnIdx);
    }

    @Override
    public boolean setFieldStateInRange(@NonNull Game game, int indeksPola, @NonNull FieldState fieldState) {
        return game.setField(indeksPola, this.columnIdx, fieldState);
    }

    @Override
    public FieldsRange getDefinedFieldsRange(@NonNull Game game) {
        return game.getRowCount() > 0
                ? FieldsRange.createRange(1, game.getRowCount())
                : FieldsRange.createUndefined();
    }

    @Override
    public FieldsSequences getDefinedSequences(@NonNull Game game) {
        return game.getVerticalSequencesAt(this.columnIdx);
    }

    @Override
    public String toString() {
        return "translator kolumny " + this.columnIdx;
    }
}