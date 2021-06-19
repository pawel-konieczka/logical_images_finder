package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

class AbstractTestClassForColStrategies extends AbstractTestClassForStrategies {

    public AbstractTestClassForColStrategies(@NonNull AbstractStrategy strategia) {
        super(strategia);
    }

    @Override
    protected Game createGame(@NonNull String input) {
        return new Game(calculateRowsCount(input), 1);
    }

    @Override
    protected String formatResult(@NonNull String input) {
        final int idx = calculateRowsCount(input);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idx; i++) {
            sb.append(input.charAt(i)).append("|\n");
        }
        sb.append("-\n");
        final String[] sequencesInfo = input.substring(idx + 1).split(",");
        for (String s : sequencesInfo) {
            sb.append(s).append("\n");
        }
        sb.append(" \n");
        return sb.toString();
    }

    @Override
    protected void setupSequences(@NonNull Game game, FieldSequence... sequences) {
        game.setVerticalSequences(1, sequences == null ? FieldsSequences.empty() : new FieldsSequences(sequences));
    }

    @Override
    protected void setupFields(@NonNull Game game, @NonNull String input) {
        for (int i = 0; i < calculateRowsCount(input); i++) {
            game.setField(i + 1, 1, FieldState.fromCode(input.charAt(i)));
        }
    }

    private int calculateRowsCount(@NonNull String input) {
        int idx = input.indexOf('|');
        if (idx < 0)
            throw new IllegalArgumentException("Invalid input format: no '|' separator");
        return idx;
    }
}