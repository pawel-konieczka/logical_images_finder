package pl.konieczki.logicalimages.model;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FieldsSequences {

    private static final FieldsSequences EMPTY = new FieldsSequences((int[]) null);

    private final FieldSequence[] sequences;

    public FieldsSequences(int... sequence) {
        if (sequence == null)
            this.sequences = new FieldSequence[0];
        else {
            this.sequences = new FieldSequence[sequence.length];
            for (var i = 0; i < sequence.length; i++) {
                this.sequences[i] = new FieldSequence(i + 1, sequence[i]);
            }
        }
    }

    public FieldsSequences(FieldSequence... sequence) {
        this.sequences = sequence;
    }

    public int getCount() {
        return sequences.length;
    }

    public boolean isEmpty() {
        return this.sequences.length == 0;
    }

    public FieldSequence getFirst() {
        if (sequences.length == 0)
            return null;
        return sequences[0];
    }

    public FieldSequence getLast() {
        if (sequences.length == 0)
            return null;
        return sequences[sequences.length - 1];
    }

    public FieldSequence getSequence(int i) {
        return sequences[i];
    }

    public FieldSequence getSequenceById(int id) {
        for (FieldSequence fieldSequence : sequences) {
            if (fieldSequence.getId() == id)
                return fieldSequence;
        }
        return null;
    }

    public int getIndexOf(FieldSequence fieldSequence) {
        if (fieldSequence == null)
            return -1;
        for (var i = 0; i < sequences.length; i++)
            if (sequences[i] == fieldSequence)
                return i;
        return -1;
    }

    public FieldSequence findSequenceOnField(int fieldIndex) {
        for (FieldSequence fieldSequence : sequences) {
            if (fieldSequence.isIdentified() && fieldSequence.getFieldFrom() <= fieldIndex && fieldIndex <= fieldSequence.getFieldTo())
                return fieldSequence;
        }
        return null;
    }

    public FieldSequence findLastCompletedSequenceFromBeginningBeforeGivenField(int pozycja) {
        for (int i = this.sequences.length - 1; i >= 0; i--) {
            final var fieldSequence = this.sequences[i];
            if (fieldSequence.isCompleted() && fieldSequence.getFieldTo() < pozycja)
                return fieldSequence;
        }
        return null;
    }

    public FieldSequence findLastCompletedSequenceFromEndingAfterGivenField(int pozycja) {
        for (final FieldSequence fieldSequence : this.sequences) {
            if (fieldSequence.isCompleted() && fieldSequence.getFieldFrom() > pozycja)
                return fieldSequence;
        }
        return null;
    }

    public boolean checkIfAllSequencesAreCompleted() {
        return checkIfAllSequencesAreGeneric(FieldSequence::isCompleted);
    }

    public boolean checkIfAllSequencesAreIdentified() {
        return checkIfAllSequencesAreGeneric(FieldSequence::isIdentified);
    }

    private boolean checkIfAllSequencesAreGeneric(Predicate<FieldSequence> isFunction) {
        for (FieldSequence fieldSequence : this.sequences) {
            if (!isFunction.test(fieldSequence))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Stream.of(this.sequences)
                .map(FieldSequence::getLength)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public static FieldsSequences empty() {
        return EMPTY;
    }

    public FieldsSequences createSubSequences(int startSequence, int endSequence) {
        if (startSequence > endSequence || startSequence < 0 || endSequence >= sequences.length)
            return FieldsSequences.empty();
        if (startSequence == 0 && endSequence == this.sequences.length - 1)
            return this;
        final int len = endSequence - startSequence + 1;
        final var result = new FieldSequence[len];
        System.arraycopy(this.sequences, startSequence, result, 0, len);
        return new FieldsSequences(result);
    }
}