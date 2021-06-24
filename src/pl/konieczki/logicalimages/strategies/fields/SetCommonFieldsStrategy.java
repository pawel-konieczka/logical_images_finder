package pl.konieczki.logicalimages.strategies.fields;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.*;
import pl.konieczki.logicalimages.strategies.AbstractStrategy;
import pl.konieczki.logicalimages.translator.GameFieldTranslator;

import java.util.Arrays;

/**
 * Wyznacza pola wspólne dla skrajnych położeń (wszystkie do początku przekrój wszystkie do końca).
 * Strategia polowa.
 */
public class SetCommonFieldsStrategy extends AbstractStrategy {

    private static final int ID_NO_INDICATOR = -1;

    public SetCommonFieldsStrategy(@NonNull GameFieldTranslator translator) {
        super(translator);
    }

    @Override
    protected boolean internalFind(Game game) {
        final var sequences = translator.getSequences(game);
        if (sequences.isEmpty()) {
            return markNoMoreChangesPossibility();
        }
        final var fieldsRange = translator.getFieldsRange(game);
        // dopasowane od początku
        final var fieldsIndicatorsFromBeginning = prepareIndicatorArray(game);
        var fieldIdx = fieldsRange.getFieldFrom();
        for (var i = 0; i < sequences.getCount(); i++) {
            final var sequence = sequences.getSequence(i);
            final var endFieldIdx = findEndingField(fieldIdx, sequence.getLength(), game);
            if (endFieldIdx <= FieldsRange.UNDEFINED) // nie dało się wpasować
                return false;
            markFieldFromEnding(endFieldIdx, sequence, fieldsIndicatorsFromBeginning);
            fieldIdx = endFieldIdx + 2;
        }
        // dopasowane od końca
        final var fieldsIndicatorsFromEnding = prepareIndicatorArray(game);
        fieldIdx = fieldsRange.getFieldTo();
        for (var i = sequences.getCount() - 1; i >= 0; i--) {
            final var sequence = sequences.getSequence(i);
            final var startFieldIdx = findBeginningField(fieldIdx, sequence.getLength(), game);
            if (startFieldIdx <= FieldsRange.UNDEFINED) // nie dało się wpasować
                return false;
            markFieldFromBeginning(startFieldIdx, sequence, fieldsIndicatorsFromEnding);
            fieldIdx = startFieldIdx - 2;
        }
        // część wspólna
        final var commonFieldsIndicatorsArray = calculateCommonIndicators(
                fieldsIndicatorsFromBeginning, fieldsIndicatorsFromEnding, sequences
        );
        var anyChange = false;
        for (var p = fieldsRange.getFieldFrom(); p <= fieldsRange.getFieldTo(); p++) {
            if (commonFieldsIndicatorsArray[p - 1] == FieldState.FULL) {
                final var b = translator.setFieldStateInRange(game, p, FieldState.FULL);
                anyChange = anyChange || b;
            }
        }
        return anyChange;
    }

    private int[] prepareIndicatorArray(Game game) {
        final var result = new int[translator.getDefinedFieldsRange(game).getLength()];
        Arrays.fill(result, ID_NO_INDICATOR);
        // jeśli są w zakresie ciągi kompletne, od razu je oznacza jako elementy stałe
        final var sequences = translator.getDefinedSequences(game);
        for (var i = 0; i < sequences.getCount(); i++) {
            final var sequence = sequences.getSequence(i);
            if (sequence.isCompleted()) {
                Arrays.fill(result, sequence.getFieldFrom() - 1, sequence.getFieldTo(), sequence.getId());
            }
        }
        return result;
    }

    private FieldState[] calculateCommonIndicators(
            int[] indicatorsFromBeginning, int[] indicatorsFromEnding, FieldsSequences sequences
    ) {
        final var result = new FieldState[indicatorsFromBeginning.length];
        for (var i = 0; i < result.length; i++) {
            if ((indicatorsFromBeginning[i] != ID_NO_INDICATOR) && (indicatorsFromBeginning[i] == indicatorsFromEnding[i])) {
                updateSequenceRange(sequences.getSequenceById(indicatorsFromBeginning[i]), i + 1);
                result[i] = FieldState.FULL;
            } else
                result[i] = FieldState.UNDEFINED;
        }
        return result;
    }

    private void updateSequenceRange(FieldSequence sequence, int p) {
        if (sequence != null) {
            if ((sequence.getFieldFrom() > p) || (sequence.getFieldFrom() == FieldsRange.UNDEFINED))
                sequence.setFieldFrom(p);
            if ((sequence.getFieldTo() < p) || (sequence.getFieldTo() == FieldsRange.UNDEFINED))
                sequence.setFieldTo(p);
        }
    }

    private void markFieldFromBeginning(int startFieldIdx, FieldSequence sequence, int[] fieldsIndicatorsArray) {
        for (var i = 0; i < sequence.getLength(); i++)
            fieldsIndicatorsArray[startFieldIdx + i - 1] = sequence.getId();
    }

    private int findBeginningField(int fieldIdx, int sequenceLength, Game game) {
        for (var p = fieldIdx; p >= sequenceLength; p--) {
            var possibleToFill = true;
            for (var i = 0; i < sequenceLength; i++) {
                if (translator.getFieldStateInRange(game, p - i) == FieldState.EMPTY) {
                    possibleToFill = false;
                    break;
                }
            }
            if (possibleToFill)
                return p - sequenceLength + 1;
        }
        return FieldsRange.UNDEFINED;
    }

    private void markFieldFromEnding(int endFieldIdx, FieldSequence sequence, int[] fieldsIndicatorsArray) {
        for (var i = 0; i < sequence.getLength(); i++)
            fieldsIndicatorsArray[endFieldIdx - i - 1] = sequence.getId();
    }

    private int findEndingField(int fieldIdx, int sequenceLength, Game game) {
        for (var p = fieldIdx; p <= translator.getFieldsRangeEnd(game) - sequenceLength + 1; p++) {
            var possibleToFill = true;
            for (var i = 0; i < sequenceLength; i++) {
                if (translator.getFieldStateInRange(game, p + i) == FieldState.EMPTY) {
                    possibleToFill = false;
                    break;
                }
            }
            if (possibleToFill)
                return p + sequenceLength - 1;
        }
        return FieldsRange.UNDEFINED;
    }
}