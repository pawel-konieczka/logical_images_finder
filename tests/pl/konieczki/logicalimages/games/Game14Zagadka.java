package pl.konieczki.logicalimages.games;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=14
 */
public class Game14Zagadka extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(5, 30);
        game.setVerticalSequences(1, new FieldsSequences(1, 2));
        game.setVerticalSequences(2, new FieldsSequences(1, 1, 1));
        game.setVerticalSequences(3, new FieldsSequences(2, 1));
        game.setVerticalSequences(4, FieldsSequences.empty());
        game.setVerticalSequences(5, new FieldsSequences(5));
        game.setVerticalSequences(6, new FieldsSequences(1, 1));
        game.setVerticalSequences(7, new FieldsSequences(5));
        game.setVerticalSequences(8, FieldsSequences.empty());
        game.setVerticalSequences(9, new FieldsSequences(5));
        game.setVerticalSequences(10, new FieldsSequences(1, 1));
        game.setVerticalSequences(11, new FieldsSequences(1, 1, 1));
        game.setVerticalSequences(12, new FieldsSequences(1, 3));
        game.setVerticalSequences(13, FieldsSequences.empty());
        game.setVerticalSequences(14, new FieldsSequences(5));
        game.setVerticalSequences(15, new FieldsSequences(1, 1));
        game.setVerticalSequences(16, new FieldsSequences(5));
        game.setVerticalSequences(17, FieldsSequences.empty());
        game.setVerticalSequences(18, new FieldsSequences(5));
        game.setVerticalSequences(19, new FieldsSequences(1, 1));
        game.setVerticalSequences(20, new FieldsSequences(1, 1));
        game.setVerticalSequences(21, new FieldsSequences(3));
        game.setVerticalSequences(22, FieldsSequences.empty());
        game.setVerticalSequences(23, new FieldsSequences(5));
        game.setVerticalSequences(24, new FieldsSequences(1));
        game.setVerticalSequences(25, new FieldsSequences(1, 1));
        game.setVerticalSequences(26, new FieldsSequences(1, 1));
        game.setVerticalSequences(27, FieldsSequences.empty());
        game.setVerticalSequences(28, new FieldsSequences(5));
        game.setVerticalSequences(29, new FieldsSequences(1, 1));
        game.setVerticalSequences(30, new FieldsSequences(5));
        game.setHorizontalSequences(1, new FieldsSequences(3, 3, 4, 3, 3, 1, 1, 3));
        game.setHorizontalSequences(2, new FieldsSequences(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        game.setHorizontalSequences(3, new FieldsSequences(1, 3, 1, 2, 3, 1, 1, 2, 3));
        game.setHorizontalSequences(4, new FieldsSequences(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
        game.setHorizontalSequences(5, new FieldsSequences(3, 1, 1, 4, 1, 1, 3, 1, 1, 1, 1));
        return game;
    }

    @Override
    protected void addHints(@NonNull Game game) {
        game.setField(1, 26, FieldState.FULL);
        game.setField(5, 26, FieldState.FULL);
    }
}