package pl.konieczki.logicalimages.translator;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsRange;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

public interface GameFieldTranslator {

    boolean recalculateFieldsRangeAndSequences(@NonNull Game game);

    boolean checkIfAllFieldsMarkedInRange(@NonNull Game game);

    boolean setAllFieldsInRangeTo(@NonNull Game game, int odPola, int doPola, @NonNull FieldState stan);

    boolean setAllFieldsInRangeTo(@NonNull Game game, @NonNull FieldsRange fieldsRange, @NonNull FieldState stan);

    boolean setAllFieldsInRangeTo(@NonNull Game game, @NonNull FieldState stan);

    boolean checkSequencesExistence(@NonNull Game game);

    FieldsSequences getSequences(@NonNull Game game);

    FieldsRange getFieldsRange(@NonNull Game game);

    int getFieldsRangeStart(@NonNull Game game);

    int getFieldsRangeEnd(@NonNull Game game);

    FieldState getFieldStateInRange(@NonNull Game game, int indeksPola);

    boolean setFieldStateInRange(@NonNull Game game, int indeksPola, @NonNull FieldState fieldState);

    FieldsRange getDefinedFieldsRange(@NonNull Game game);

    FieldsSequences getDefinedSequences(@NonNull Game game);

    int findFirstFieldInRangeWithStateFromBeginning(@NonNull FieldsRange fieldsRange, @NonNull Game game, @NonNull FieldState fieldState);

    int findFirstFieldInRangeWithStateFromBeginning(@NonNull Game game, @NonNull FieldState fieldState);

    int findFirstFieldInRangeWithStateFromEnding(@NonNull FieldsRange fieldsRange, @NonNull Game game, @NonNull FieldState fieldState);

    int findFirstFieldInRangeWithStateFromEnding(@NonNull Game game, @NonNull FieldState fieldState);
}
