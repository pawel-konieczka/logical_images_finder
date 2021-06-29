package pl.konieczki.logicalimages.strategies.sequences;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.*;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Strategia oznacza brzegowe pola {@link FieldState#FULL} jako przynależne dla pierwszego (od początku lub końca)
 * ciągu. Najpierw szuka pierwszego od początku/końca pełnego pola i jeśli długość pierwszego/ostatniego ciągu
 * jest większa, równa lub mniejsza o jeden, wówczas to pole wiążemy z pierwszym/ostatnim ciągiem.
 * Strategia dla ciągów.
 */
public class MarkFirstSequenceWhenFirstFullFieldIsInRangeOfSequenceStrategy extends AbstractSequencesStrategy {

    public MarkFirstSequenceWhenFirstFullFieldIsInRangeOfSequenceStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean internalFind(@NonNull Game game) {
        final var sequences = translator.getSequences(game);
        if (sequences.isEmpty())
            return markNoMoreChangesPossibility();
        final boolean b1 = markFirstSequenceFromBeginning(sequences, game); // ciurkiem od początku
        final boolean b2 = markFirstSequenceFromEnding(sequences, game); // ciurkiem od końca
        return b1 || b2;
    }

    private boolean markFirstSequenceFromBeginning(@NonNull FieldsSequences fieldsSequences, @NonNull Game game) {
        final var firstSequence = fieldsSequences.getFirst();
        final var rangeStart = translator.getFieldsRangeStart(game);
        final var rangeToCheck = FieldsRange.createRange(rangeStart, rangeStart + firstSequence.getLength() - 1);
        return markSelectedSequenceWithRangeToCheck(firstSequence, game, rangeToCheck);
    }

    private boolean markFirstSequenceFromEnding(FieldsSequences fieldsSequences, Game game) {
        final var lastSequence = fieldsSequences.getLast();
        final var rangeEnd = translator.getFieldsRangeEnd(game);
        final var rangeToCheck = FieldsRange.createRange(rangeEnd - lastSequence.getLength() + 1, rangeEnd);
        return markSelectedSequenceWithRangeToCheck(lastSequence, game, rangeToCheck);
    }

    private boolean markSelectedSequenceWithRangeToCheck(
            @NonNull FieldSequence sequence, @NonNull Game game, @NonNull FieldsRange rangeToCheck
    ) {
        var b1 = false;
        var b2 = false;
        // start range
        final var firstFullFieldInRangeIdx =
                translator.findFirstFieldInRangeWithStateFromBeginning(rangeToCheck, game, FieldState.FULL);
        if (firstFullFieldInRangeIdx == FieldsRange.UNDEFINED)
            return false;
        if (sequence.getFieldFrom() == FieldsRange.UNDEFINED || sequence.getFieldFrom() > firstFullFieldInRangeIdx) {
            b1 = sequence.setFieldFrom(firstFullFieldInRangeIdx);
        }
        // end range
        final var lastFullFieldInRangeIdx =
                translator.findFirstFieldInRangeWithStateFromEnding(rangeToCheck, game, FieldState.FULL);
        if (lastFullFieldInRangeIdx == FieldsRange.UNDEFINED)
            return false;
        if (sequence.getFieldTo() == FieldsRange.UNDEFINED || sequence.getFieldTo() < lastFullFieldInRangeIdx) {
            b2 = sequence.setFieldTo(lastFullFieldInRangeIdx);
        }
        return b1 || b2;
    }
}