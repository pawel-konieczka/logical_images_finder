package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsRange;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Strategia oznacza brzegowe pola {@link FieldState#FULL} jako przynależne dla pierwszego (od początku lub końca)
 * ciągu. Najpierw szuka pierwszego od początku/końca pełnego pola i jeśli długość pierwszego/ostatniego ciągu
 * jest większa, równa lub mniejsza o jeden, wówczas to pole wiążemy z pierwszym/ostatnim ciągiem.
 * Strategia dla ciągów.
 */
public class MarkFirstSequenceWhenFirstFullFieldIsInRangeOfSequenceStrategy extends AbstractStrategy {

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

    @Override
    protected boolean checkIfAllFieldsProperlyMarked(@NonNull Game game) {
        return translator.getSequences(game).checkIfAllSequencesAreCompleted();
    }

    private boolean markFirstSequenceFromBeginning(@NonNull FieldsSequences fieldsSequences, @NonNull Game game) {
        final int firstFullFieldIdx = translator.findFirstFieldInRangeWithStateFromBeginning(game, FieldState.FULL);
        if (firstFullFieldIdx == FieldsRange.UNDEFINED)
            return false;
        final var firstSequence = fieldsSequences.getFirst();
        if ((firstSequence.getFieldFrom() == FieldsRange.UNDEFINED || firstSequence.getFieldFrom() > firstFullFieldIdx)
                && translator.getFieldsRangeStart(game) + firstSequence.getLength() >= firstFullFieldIdx) {
            return firstSequence.setFieldFrom(firstFullFieldIdx);
        }
        return false;
    }

    private boolean markFirstSequenceFromEnding(FieldsSequences fieldsSequences, Game game) {
        final int lastFullFieldIdx = translator.findFirstFieldInRangeWithStateFromEnding(game, FieldState.FULL);
        if (lastFullFieldIdx == FieldsRange.UNDEFINED)
            return false;
        final var lastSequence = fieldsSequences.getLast();
        if ((lastSequence.getFieldTo() == FieldsRange.UNDEFINED || lastSequence.getFieldTo() < lastFullFieldIdx)
                && translator.getFieldsRangeEnd(game) - lastSequence.getLength() <= lastFullFieldIdx
        ) {
            return lastSequence.setFieldTo(lastFullFieldIdx);
        }
        return false;
    }
}