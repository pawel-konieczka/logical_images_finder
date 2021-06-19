package pl.konieczki.logicalimages.translator;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.*;

public abstract class AbstractGameFieldTranslator implements GameFieldTranslator {

    private boolean initialized = false;
    private FieldsRange fieldsRange;
    private FieldsSequences fieldsSequences;

    public boolean recalculateFieldsRangeAndSequences(@NonNull Game game) {
        checkAndInit(game);
        final boolean b1 = recalculateCurrentFieldsRange(game);
        final boolean b2 = recalculateCurrentSequences(game); // musi być po wyznaczeniu zakresu pól
        return b1 || b2;
    }

    @Override
    public boolean setAllFieldsInRangeTo(@NonNull Game game, @NonNull FieldsRange fieldsRange, @NonNull FieldState stan) {
        return setAllFieldsInRangeTo(game, fieldsRange.getFieldFrom(), fieldsRange.getFieldTo(), stan);
    }

    @Override
    public boolean setAllFieldsInRangeTo(@NonNull Game game, @NonNull FieldState stan) {
        checkAndInit(game);
        return setAllFieldsInRangeTo(game, this.fieldsRange, stan);
    }

    @Override
    public boolean checkSequencesExistence(@NonNull Game game) {
        checkAndInit(game);
        return this.fieldsSequences.isEmpty();
    }

    @Override
    public FieldsSequences getSequences(@NonNull Game game) {
        checkAndInit(game);
        return this.fieldsSequences;
    }

    @Override
    public FieldsRange getFieldsRange(@NonNull Game game) {
        checkAndInit(game);
        return this.fieldsRange;
    }

    @Override
    public int getFieldsRangeStart(@NonNull Game game) {
        checkAndInit(game);
        return this.fieldsRange.getFieldFrom();
    }

    @Override
    public int getFieldsRangeEnd(@NonNull Game game) {
        checkAndInit(game);
        return this.fieldsRange.getFieldTo();
    }

    @Override
    public int findFirstFieldInRangeWithStateFromBeginning(
            @NonNull FieldsRange fieldsRange, @NonNull Game game, @NonNull FieldState fieldState
    ) {
        for (int p = fieldsRange.getFieldFrom(); p <= fieldsRange.getFieldTo(); p++) {
            if (this.getFieldStateInRange(game, p) == fieldState)
                return p;
        }
        return FieldsRange.UNDEFINED;
    }

    @Override
    public int findFirstFieldInRangeWithStateFromBeginning(@NonNull Game game, @NonNull FieldState fieldState) {
        return findFirstFieldInRangeWithStateFromBeginning(this.fieldsRange, game, fieldState);
    }

    @Override
    public int findFirstFieldInRangeWithStateFromEnding(
            @NonNull FieldsRange fieldsRange, @NonNull Game game, @NonNull FieldState fieldState
    ) {
        for (int p = fieldsRange.getFieldTo(); p >= fieldsRange.getFieldFrom(); p--) {
            if (this.getFieldStateInRange(game, p) == fieldState)
                return p;
        }
        return FieldsRange.UNDEFINED;
    }

    @Override
    public int findFirstFieldInRangeWithStateFromEnding(@NonNull Game game, @NonNull FieldState fieldState) {
        return findFirstFieldInRangeWithStateFromEnding(this.fieldsRange, game, fieldState);
    }

    private boolean recalculateCurrentSequences(@NonNull Game game) {
        final int startSequenceIdx = findFirstFromBeginningIncompleteSequenceIdx(game);
        final int endSequenceIdx = findFirstFromEndingIncompleteSequenceIdx(game);
        final int previousSequencesCount = this.fieldsSequences.getCount();
        this.fieldsSequences = this.fieldsSequences.createSubSequences(startSequenceIdx, endSequenceIdx);
        return previousSequencesCount != this.fieldsSequences.getCount();
    }

    private int findFirstFromBeginningIncompleteSequenceIdx(@NonNull Game game) {
        final int zakresOd = getFieldsRangeStart(game);
        for (var i = 0; i < this.fieldsSequences.getCount(); i++) {
            final var sequence = this.fieldsSequences.getSequence(i);
            if (!sequence.isCompleted() || sequence.getFieldTo() > zakresOd)
                return i;
        }
        return -1;
    }

    private int findFirstFromEndingIncompleteSequenceIdx(@NonNull Game game) {
        final int zakresDo = getFieldsRangeEnd(game);
        for (int i = this.fieldsSequences.getCount() - 1; i >= 0; i--) {
            final var sequence = this.fieldsSequences.getSequence(i);
            if (!sequence.isCompleted() || sequence.getFieldFrom() < zakresDo)
                return i;
        }
        return -1;
    }

    private boolean recalculateCurrentFieldsRange(@NonNull Game game) {
        final int zakresOd = findFirstUndefinedFieldFromBeginning(game);
        final int zakresDo = findFirstUndefinedFieldFromEnding(game);
        final boolean result = this.fieldsRange.getFieldFrom() != zakresOd || this.fieldsRange.getFieldTo() != zakresDo;
        if (result)
            this.fieldsRange = FieldsRange.createRange(zakresOd, zakresDo);
        return result;
    }

    private int findFirstUndefinedFieldFromBeginning(@NonNull Game game) {
        int p = this.fieldsRange.getFieldFrom();
        while (p <= this.fieldsRange.getFieldTo()) {
            switch (getFieldStateInRange(game, p)) {
                case UNDEFINED:
                    return p;
                case EMPTY:
                    p++;
                    break;
                case FULL:
                    final FieldSequence sequenceOnField = fieldsSequences.findSequenceOnField(p);
                    // jeśli nie trafiłeś na kompletny ciąg
                    if (sequenceOnField == null || !sequenceOnField.isCompleted())
                        return p;
                    // jeśli ciąg jest kompletny, ale nie jest zakończony pustym polem
                    if ((sequenceOnField.getFieldTo() < getFieldsRangeEnd(game))
                            && (FieldState.EMPTY != getFieldStateInRange(game, sequenceOnField.getFieldTo() + 1))
                    )
                        return p;
                    p = sequenceOnField.getFieldTo() + 1;
                    break;
                default:
                    throw new IllegalStateException("Nieobsługiwany stan pola");
            }
        }
        return getDefinedFieldsRange(game).getFieldFrom();
    }

    private int findFirstUndefinedFieldFromEnding(@NonNull Game game) {
        int p = this.fieldsRange.getFieldTo();
        while (p >= this.fieldsRange.getFieldFrom()) {
            switch (getFieldStateInRange(game, p)) {
                case UNDEFINED:
                    return p;
                case EMPTY:
                    p--;
                    break;
                case FULL:
                    final FieldSequence sequenceOnField = fieldsSequences.findSequenceOnField(p);
                    // jeśli nie trafiłeś na kompletny ciąg
                    if (sequenceOnField == null || !sequenceOnField.isCompleted())
                        return p;
                    // jeśli ciąg jest kompletny, ale nie jest zakończony pustym polem
                    if ((sequenceOnField.getFieldFrom() > getFieldsRangeStart(game))
                            && (FieldState.EMPTY != getFieldStateInRange(game, sequenceOnField.getFieldFrom() - 1))
                    )
                        return p;
                    p = sequenceOnField.getFieldFrom() - 1;
                    break;
                default:
                    throw new IllegalStateException("Nieobsługiwany stan pola");
            }
        }
        return getDefinedFieldsRange(game).getFieldTo();
    }

    private void checkAndInit(@NonNull Game game) {
        if (!this.initialized) {
            this.fieldsRange = getDefinedFieldsRange(game);
            this.fieldsSequences = getDefinedSequences(game);
            this.initialized = true;
        }
    }
}