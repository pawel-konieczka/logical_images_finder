package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Dla oznaczonego, niekompletnego, pierwszego od brzegu ciągu sprawdza, czy można go wydłużyć ku środkowi do długości
 * tego ciągu.
 * Strategia dla ciągów.
 */
public class ExpandFieldsWithSequenceLengthStrategy extends AbstractStrategy {

    public ExpandFieldsWithSequenceLengthStrategy(@NonNull GameFieldTranslator translator) {
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
        if (firstSequence.isCompleted())
            return false;
        final var rangeStart = translator.getFieldsRangeStart(game);
        final var sequenceEnd = rangeStart + firstSequence.getLength() - 1;
        if (firstSequence.isIdentified()) {
            if (firstSequence.getFieldTo() < sequenceEnd)
                return firstSequence.setFieldTo(sequenceEnd);
        } else {
            // all fields should be undefined in range
            for (var p = rangeStart; p <= sequenceEnd; p++)
                if (translator.getFieldStateInRange(game, p) != FieldState.UNDEFINED)
                    return false;
            if (translator.getFieldStateInRange(game, sequenceEnd + 1) == FieldState.FULL)
                return firstSequence.setFieldFrom(sequenceEnd + 1);
        }
        return false;
    }

    private boolean expandFirstSequenceFromEnding(@NonNull FieldsSequences sequences, @NonNull Game game) {
        final var lastSequence = sequences.getLast();
        if (lastSequence.isCompleted())
            return false;
        final var rangeEnd = translator.getFieldsRangeEnd(game);
        final var sequenceStart = rangeEnd - lastSequence.getLength() + 1;
        if (lastSequence.isIdentified()) {
            if (lastSequence.getFieldFrom() > sequenceStart)
                return lastSequence.setFieldFrom(sequenceStart);
        } else {
            // all fields should be undefined in range
            for (var p = rangeEnd; p >= sequenceStart; p--)
                if (translator.getFieldStateInRange(game, p) != FieldState.UNDEFINED)
                    return false;
            if (translator.getFieldStateInRange(game, sequenceStart - 1) == FieldState.FULL)
                return lastSequence.setFieldTo(sequenceStart - 1);
        }
        return false;
    }
}