package pl.konieczki.logicalimages;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

import java.util.ArrayList;

public class LogicalImageDecoder {

    public Game parseFromString(int rowsCount, int columnsCount, @NonNull String fields) {
        final var lines = fields.split("[\\r\\n]");
        if (lines.length != rowsCount)
            throw new IllegalArgumentException(
                    String.format("Invalid row count (found in input: %d, declared: %d)", lines.length, rowsCount)
            );
        final var result = new Game(rowsCount, columnsCount);
        for (var l = 0; l < lines.length; l++) {
            final var preparedLine = processLine(lines[l]);
            if (preparedLine.length() != columnsCount)
                throw new IllegalArgumentException(
                        String.format("Invalid column count (found in input: %d, declared: %d)", preparedLine.length(), columnsCount)
                );
            updateGameFields(result, l + 1, preparedLine);
        }
        return result;
    }

    public Game decode(@NonNull Game game) {
        if (!game.isFullMarked())
            throw new IllegalStateException("Image is not full marked and can't be decoded.");
        final var result = new Game(game.getRowCount(), game.getColCount());
        for (var r = 1; r <= game.getRowCount(); r++)
            result.setHorizontalSequences(r, createHorizontalSequences(game, r));
        for (var c = 1; c <= game.getColCount(); c++)
            result.setVerticalSequences(c, createVerticalSequences(game, c));

        return result;
    }

    private FieldsSequences createVerticalSequences(Game game, int col) {
        var lengthList = new ArrayList<Integer>();
        var previousState = game.getField(1, col);
        var count = previousState == FieldState.FULL ? 1 : 0;
        for (var r = 2; r <= game.getRowCount(); r++) {
            var state = game.getField(r, col);
            if (state == FieldState.FULL)
                count++;
            else {
                if (previousState != state) {
                    lengthList.add(count);
                    count = 0;
                }
            }
            previousState = state;
        }
        if (previousState == FieldState.FULL)
            lengthList.add(count);
        return new FieldsSequences(lengthList.stream().mapToInt(Integer::intValue).toArray());
    }

    private FieldsSequences createHorizontalSequences(Game game, int row) {
        var lengthList = new ArrayList<Integer>();
        var previousState = game.getField(row, 1);
        var count = previousState == FieldState.FULL ? 1 : 0;
        for (var c = 2; c <= game.getColCount(); c++) {
            var state = game.getField(row, c);
            if (state == FieldState.FULL)
                count++;
            else {
                if (previousState != state) {
                    lengthList.add(count);
                    count = 0;
                }
            }
            previousState = state;
        }
        if (previousState == FieldState.FULL)
            lengthList.add(count);
        return new FieldsSequences(lengthList.stream().mapToInt(Integer::intValue).toArray());
    }

    private void updateGameFields(@NonNull Game game, int rowId, @NonNull String line) {
        for (var c = 0; c < game.getColCount(); c++) {
            game.setField(rowId, c + 1, FieldState.fromCode(line.charAt(c)));
        }
    }

    private String processLine(@NonNull String line) {
        final var sb = new StringBuilder();
        for (var i = 0; i < line.length(); i++) {
            final var c = line.charAt(i);
            if (FieldState.isDefined(c))
                sb.append(c);
            else if (c == '|')
                break;
            else throw new IllegalArgumentException("Unrecognized character: " + c);
        }
        return sb.toString();
    }
}