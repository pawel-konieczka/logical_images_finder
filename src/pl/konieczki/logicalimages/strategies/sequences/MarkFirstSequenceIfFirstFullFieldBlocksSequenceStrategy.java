package pl.konieczki.logicalimages.strategies.sequences;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Dla nieoznaczonego, pierwszego od brzegu ciągu sprawdza, czy można przed pierwszym pełnym polem
 * wstawić ten ciąg.
 * Strategia dla ciągów.
 */
public class MarkFirstSequenceIfFirstFullFieldBlocksSequenceStrategy extends AbstractSequencesStrategy {

    public MarkFirstSequenceIfFirstFullFieldBlocksSequenceStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean internalFind(@NonNull Game game) {
        final var sequences = translator.getSequences(game);
        if (sequences.isEmpty())
            return markNoMoreChangesPossibility();
        final boolean b1 = expandFirstSequenceFromBeginning(sequences, game);
        final boolean b2 = expandFirstSequenceFromEnding(sequences, game);
        return b1 || b2;
    }

    private boolean expandFirstSequenceFromBeginning(@NonNull FieldsSequences sequences, @NonNull Game game) {
        final var firstSequence = sequences.getFirst();
        if (firstSequence.isIdentified())
            return false;
        final var fieldsRange = translator.getFieldsRange(game);
        final var sequenceEnd = fieldsRange.getFieldFrom() + firstSequence.getLength() - 1;
        if (sequenceEnd >= fieldsRange.getFieldTo())
            return false;
        // all fields should be undefined in range
        for (var p = fieldsRange.getFieldFrom(); p <= sequenceEnd; p++)
            if (translator.getFieldStateInRange(game, p) != FieldState.UNDEFINED)
                return false;
        if (translator.getFieldStateInRange(game, sequenceEnd + 1) == FieldState.FULL)
            return firstSequence.setFieldFrom(sequenceEnd + 1);
        return false;
    }

    private boolean expandFirstSequenceFromEnding(@NonNull FieldsSequences sequences, @NonNull Game game) {
        final var lastSequence = sequences.getLast();
        if (lastSequence.isIdentified())
            return false;
        final var fieldsRange = translator.getFieldsRange(game);
        final var sequenceStart = fieldsRange.getFieldTo() - lastSequence.getLength() + 1;
        if (sequenceStart <= fieldsRange.getFieldFrom())
            return false;
        // all fields should be undefined in range
        for (var p = fieldsRange.getFieldTo(); p >= sequenceStart; p--)
            if (translator.getFieldStateInRange(game, p) != FieldState.UNDEFINED)
                return false;
        if (translator.getFieldStateInRange(game, sequenceStart - 1) == FieldState.FULL)
            return lastSequence.setFieldTo(sequenceStart - 1);
        return false;
    }
}