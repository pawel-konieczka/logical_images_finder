package pl.konieczki.logicalimages;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

public class LogicalImageValidator {

    public boolean validate(@NonNull Game input) {
        final var decoder = new LogicalImageDecoder();
        final var decodedGame = decoder.decode(input);
        if (decodedGame.getColCount() != input.getColCount() || decodedGame.getRowCount() != input.getRowCount())
            return false;
        for (var r = 1; r <= input.getRowCount(); r++) {
            final var inputFS = input.getHorizontalSequencesAt(r);
            final var decodedGameFS = decodedGame.getHorizontalSequencesAt(r);
            if (notEqualFieldsSequences(inputFS, decodedGameFS))
                return false;
        }
        for (var c = 1; c <= input.getColCount(); c++) {
            final var inputFS = input.getVerticalSequencesAt(c);
            final var decodedGameFS = decodedGame.getVerticalSequencesAt(c);
            if (notEqualFieldsSequences(inputFS, decodedGameFS))
                return false;
        }
        return true;
    }

    private boolean notEqualFieldsSequences(@NonNull FieldsSequences inputFS, @NonNull FieldsSequences decodedGameFS) {
        if (inputFS.getCount() != decodedGameFS.getCount())
            return true;
        for (var i = 0; i < inputFS.getCount(); i++) {
            if (inputFS.getSequence(i).getLength() != decodedGameFS.getSequence(i).getLength())
                return true;
        }
        return false;
    }
}