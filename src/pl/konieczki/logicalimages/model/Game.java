package pl.konieczki.logicalimages.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {

    @Getter
    private final int rowCount;
    @Getter
    private final int colCount;
    private final FieldState[] fieldStates;
    private final FieldsSequences[] verticalSequences;
    private final FieldsSequences[] horizontalSequences;

    public Game(int rowCount, int colCount) {
        this.colCount = colCount;
        this.rowCount = rowCount;
        this.fieldStates = new FieldState[colCount * rowCount];
        Arrays.fill(this.fieldStates, FieldState.UNDEFINED);
        this.verticalSequences = new FieldsSequences[this.colCount];
        Arrays.fill(this.verticalSequences, FieldsSequences.empty());
        this.horizontalSequences = new FieldsSequences[this.rowCount];
        Arrays.fill(this.horizontalSequences, FieldsSequences.empty());
    }

    public FieldState getField(int row, int col) {
        return this.fieldStates[toIdx(row, col)];
    }

    public boolean setField(int row, int col, @NonNull FieldState stan) {
        final int idx = toIdx(row, col);
        final FieldState oldStan = this.fieldStates[idx];
        if (oldStan != FieldState.UNDEFINED && oldStan != stan)
            throw new IllegalArgumentException(
                    "Niedozwolona zmiana stanu z '" + oldStan.getMarker() + "' na '" + stan.getMarker()
                            + "' w polu w=" + row + ", k=" + col
            );
        final boolean result = (stan != oldStan);
        this.fieldStates[idx] = stan;
        return result;
    }

    public FieldsSequences getVerticalSequencesAt(int col) {
        return this.verticalSequences[col - 1];
    }

    public void setVerticalSequences(int col, FieldsSequences sequences) {
        this.verticalSequences[col - 1] = sequences;
    }

    public FieldsSequences getHorizontalSequencesAt(int row) {
        return this.horizontalSequences[row - 1];
    }

    public void setHorizontalSequences(int row, FieldsSequences sequences) {
        this.horizontalSequences[row - 1] = sequences;
    }

    public boolean setAllFieldsInRangeTo(
            int rowFrom, int rowTo, int columnFrom, int columnTo, FieldState state
    ) {
        var anyChange = false;
        for (int r = rowFrom; r <= rowTo; r++)
            for (int c = columnFrom; c <= columnTo; c++)
                anyChange = this.setField(r, c, state) || anyChange;
        return anyChange;
    }

    public boolean checkIfAllFieldsMarkedInRange(int rowFrom, int rowTo, int colFrom, int colTo) {
        for (int r = rowFrom; r <= rowTo; r++)
            for (int c = colFrom; c <= colTo; c++)
                if (this.getField(r, c) == FieldState.UNDEFINED)
                    return false;
        return true;
    }

    public boolean isFullMarked() {
        return checkIfAllFieldsMarkedInRange(1, this.rowCount, 1, this.colCount);
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        for (var r = 1; r <= this.rowCount; r++) {
            sb.append(rowToString(r)).append("\n");
        }
        sb.append("-".repeat(this.colCount)).append("\n");
        final Map<Integer, Character> toLongNumberMap = new HashMap<>();
        var shouldContinue = true;
        var i = 0;
        while (shouldContinue) {
            shouldContinue = false;
            for (var k = 1; k <= this.colCount; k++) {
                final FieldsSequences sequences = getVerticalSequencesAt(k);
                if (sequences.getCount() > i) {
                    sb.append(checkToLongNumbers(sequences.getSequence(i).getLength(), toLongNumberMap));
                    shouldContinue = true;
                } else {
                    sb.append(' ');
                }
            }
            sb.append("\n");
            i++;
        }
        if (!toLongNumberMap.isEmpty())
            sb.append(mapToString(toLongNumberMap)).append("\n");
        return sb.toString();
    }

    public String rowToString(int row) {
        final var sb = new StringBuilder();
        for (var c = 1; c <= this.colCount; c++) {
            sb.append(getField(row, c).getMarker());
        }
        sb.append('|');
        final var sequences = getHorizontalSequencesAt(row);
        for (var i = 0; i < sequences.getCount(); i++) {
            if (i > 0)
                sb.append(',');
            sb.append(sequences.getSequence(i));
        }
        return sb.toString();
    }

    public String columnToString(int col) {
        final var sb = new StringBuilder();
        for (var r = 1; r <= this.rowCount; r++) {
            sb.append(getField(r, col).getMarker());
        }
        sb.append('|');
        final var sequences = getVerticalSequencesAt(col);
        for (var i = 0; i < sequences.getCount(); i++) {
            if (i > 0)
                sb.append(',');
            sb.append(sequences.getSequence(i));
        }
        return sb.toString();
    }

    // -------- privates ------------
    private int toIdx(int row, int col) {
        return (row - 1) * this.colCount + col - 1;
    }

    private String mapToString(Map<Integer, Character> toLongNumberMap) {
        return toLongNumberMap.entrySet().stream()
                .map(ent -> ent.getValue() + " - " + ent.getKey())
                .sorted()
                .collect(Collectors.joining(", "));
    }

    private char checkToLongNumbers(int sequenceLength, Map<Integer, Character> toLongNumberMap) {
        if (sequenceLength < 10)
            return (char) (((int) '1') + (sequenceLength - 1));
        Character c = toLongNumberMap.get(sequenceLength);
        if (c != null)
            return c;
        c = (char) (((int) 'A') + toLongNumberMap.size());
        toLongNumberMap.put(sequenceLength, c);
        return c;
    }
}