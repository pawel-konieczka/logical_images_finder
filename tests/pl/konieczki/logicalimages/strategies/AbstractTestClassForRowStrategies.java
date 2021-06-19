package pl.konieczki.logicalimages.strategies;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldSequence;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

class AbstractTestClassForRowStrategies extends AbstractTestClassForStrategies {

    public AbstractTestClassForRowStrategies(@NonNull AbstractStrategy strategia) {
        super(strategia);
    }

    @Override
    protected Game createGame(@NonNull String input) {
        return new Game(1, calculateColumnsCount(input));
    }

    @Override
    protected String formatResult(@NonNull String input) {
        final int idx = calculateColumnsCount(input);
        return input + "\n" + "-".repeat(idx) + "\n" + " ".repeat(idx) + "\n";
    }

    @Override
    protected void setupSequences(@NonNull Game game, FieldSequence... sequences) {
        game.setHorizontalSequences(1, sequences == null ? FieldsSequences.empty() : new FieldsSequences(sequences));
    }

    @Override
    protected void setupFields(@NonNull Game game, @NonNull String input) {
        for (int i = 0; i < calculateColumnsCount(input); i++) {
            game.setField(1, i + 1, FieldState.fromCode(input.charAt(i)));
        }
    }

    private int calculateColumnsCount(@NonNull String input) {
        int idx = input.indexOf('|');
        if (idx < 0)
            throw new IllegalArgumentException("Invalid input format: no '|' separator");
        return idx;
    }
}