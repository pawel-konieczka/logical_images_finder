package pl.konieczki.logicalimages.strategies.sequences;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.*;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

/**
 * Strategia szuka pierwszego pola {@link FieldState#FULL}, przed którym były tylko pola pełne lub puste,
 * wiąże je z odpowiednim ciągiem i od tego pola oznacza kolejne pola jako pełne, zgodnie z długością ciągu.
 * Strategia dla ciągów.
 */
public class MarkFirstSureSequenceStrategy extends AbstractSequencesStrategy {

    public MarkFirstSureSequenceStrategy(@NonNull GameFieldTranslator translator) {
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

    private boolean markFirstSequenceFromBeginning(@NonNull FieldsSequences sequences, @NonNull Game game) {
        final int sequenceFirstFieldIdx = findFirstFieldOfSureSequenceFromBeginning(sequences, game);
        if (sequenceFirstFieldIdx == FieldsRange.UNDEFINED)
            return false;
        final FieldSequence ostatniKompletnyOdPoczatkuPrzedPodanaKolumna
                = sequences.findLastCompletedSequenceFromBeginningBeforeGivenField(sequenceFirstFieldIdx);
        final var sequenceIdx = sequences.getIndexOf(ostatniKompletnyOdPoczatkuPrzedPodanaKolumna);
        final var sequenceToUpdate =
                sequenceIdx != -1 ? sequences.getSequence(sequenceIdx + 1) : sequences.getFirst();
        final boolean b1 = sequenceToUpdate.setFieldFrom(sequenceFirstFieldIdx);
        final boolean b2 = sequenceToUpdate.setFieldTo(sequenceFirstFieldIdx + sequenceToUpdate.getLength() - 1);
        final boolean b3 = sequenceToUpdate.isCompleted()
                && translator.setAllFieldsInRangeTo(game, sequenceToUpdate, FieldState.FULL);
        return b1 || b2 || b3;
    }

    private boolean markFirstSequenceFromEnding(@NonNull FieldsSequences sequences, @NonNull Game game) {
        final int sequenceLastFieldIdx = findLastFieldOfSureSequenceFromEnding(sequences, game);
        if (sequenceLastFieldIdx == FieldsRange.UNDEFINED)
            return false;
        final FieldSequence ostatniKompletnyOdKoncaZaPodanaKolumna
                = sequences.findLastCompletedSequenceFromEndingAfterGivenField(sequenceLastFieldIdx);
        final var sequenceIdx = sequences.getIndexOf(ostatniKompletnyOdKoncaZaPodanaKolumna);
        // przed znalezionym lub ostatni
        final var sequenceToUpdate =
                sequenceIdx != -1 ? sequences.getSequence(sequenceIdx - 1) : sequences.getLast();
        final boolean b1 = sequenceToUpdate.setFieldFrom(sequenceLastFieldIdx - sequenceToUpdate.getLength() + 1);
        final boolean b2 = sequenceToUpdate.setFieldTo(sequenceLastFieldIdx);
        final boolean b3 = sequenceToUpdate.isCompleted()
                && translator.setAllFieldsInRangeTo(game, sequenceToUpdate, FieldState.FULL);
        return b1 || b2 || b3;
    }

    // idzie od lewej, przeskakuje puste pola i oznaczone ciągi, zwraca pierwsze (od lewej) pole X dla nieoznaczonego
    // ciągu lub -1 w każdym innym przypadku
    private int findFirstFieldOfSureSequenceFromBeginning(@NonNull FieldsSequences sequences, @NonNull Game game) {
        final var fieldsRange = translator.getFieldsRange(game);
        int p = fieldsRange.getFieldFrom();
        while (p <= fieldsRange.getFieldTo()) {
            switch (translator.getFieldStateInRange(game, p)) {
                case UNDEFINED:
                    return FieldsRange.UNDEFINED;
                case EMPTY:
                    p++;
                    break;
                case FULL:
                    final FieldSequence sequenceOnField = sequences.findSequenceOnField(p);
                    if (sequenceOnField == null || !sequenceOnField.isCompleted())
                        return p;
                    p = sequenceOnField.getFieldTo() + 1;
                    break;
                default:
                    throw new IllegalStateException("Nieobsługiwany stan pola");
            }
        }
        return FieldsRange.UNDEFINED;
    }

    // idzie od prawej, przeskakuje puste pola i oznaczone ciągi, zwraca pierwsze (od prawej) pole X dla nieoznaczonego
    // ciągu lub -1
    private int findLastFieldOfSureSequenceFromEnding(@NonNull FieldsSequences sequences, @NonNull Game game) {
        final var fieldsRange = translator.getFieldsRange(game);
        int p = fieldsRange.getFieldTo();
        while (p >= fieldsRange.getFieldFrom()) {
            switch (translator.getFieldStateInRange(game, p)) {
                case UNDEFINED:
                    return FieldsRange.UNDEFINED;
                case EMPTY:
                    p--;
                    break;
                case FULL:
                    final FieldSequence sequenceOnField = sequences.findSequenceOnField(p);
                    if (sequenceOnField == null || !sequenceOnField.isCompleted())
                        return p;
                    p = sequenceOnField.getFieldFrom() - 1;
                    break;
                default:
                    throw new IllegalStateException("Nieobsługiwany stan pola");
            }
        }
        return FieldsRange.UNDEFINED;
    }
}