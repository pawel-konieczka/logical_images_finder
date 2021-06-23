package pl.konieczki.logicalimages.games;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=13
 */
public class Game13Wielblad extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(15, 25);
        game.setVerticalSequences(1, FieldsSequences.empty());
        game.setVerticalSequences(2, new FieldsSequences(3));
        game.setVerticalSequences(3, new FieldsSequences(4));
        game.setVerticalSequences(4, new FieldsSequences(3, 2));
        game.setVerticalSequences(5, new FieldsSequences(4, 1, 1));
        game.setVerticalSequences(6, new FieldsSequences(3, 2));
        game.setVerticalSequences(7, new FieldsSequences(7));
        game.setVerticalSequences(8, new FieldsSequences(7, 1));
        game.setVerticalSequences(9, new FieldsSequences(12));
        game.setVerticalSequences(10, new FieldsSequences(9));
        game.setVerticalSequences(11, new FieldsSequences(5));
        game.setVerticalSequences(12, new FieldsSequences(5));
        game.setVerticalSequences(13, new FieldsSequences(4, 1));
        game.setVerticalSequences(14, new FieldsSequences(3, 2, 1));
        game.setVerticalSequences(15, new FieldsSequences(8, 1));
        game.setVerticalSequences(16, new FieldsSequences(9));
        game.setVerticalSequences(17, new FieldsSequences(6, 1));
        game.setVerticalSequences(18, new FieldsSequences(7));
        game.setVerticalSequences(19, new FieldsSequences(7));
        game.setVerticalSequences(20, new FieldsSequences(1, 5, 1));
        game.setVerticalSequences(21, new FieldsSequences(1, 4));
        game.setVerticalSequences(22, new FieldsSequences(5));
        game.setVerticalSequences(23, new FieldsSequences(2));
        game.setVerticalSequences(24, new FieldsSequences(1));
        game.setVerticalSequences(25, new FieldsSequences(2));
        game.setHorizontalSequences(1, FieldsSequences.empty());
        game.setHorizontalSequences(2, FieldsSequences.empty());
        game.setHorizontalSequences(3, new FieldsSequences(2, 1));
        game.setHorizontalSequences(4, new FieldsSequences(1, 4, 6));
        game.setHorizontalSequences(5, new FieldsSequences(17, 1));
        game.setHorizontalSequences(6, new FieldsSequences(18, 1));
        game.setHorizontalSequences(7, new FieldsSequences(19, 1));
        game.setHorizontalSequences(8, new FieldsSequences(2, 7, 6, 1));
        game.setHorizontalSequences(9, new FieldsSequences(7, 3, 3, 1));
        game.setHorizontalSequences(10, new FieldsSequences(3, 2, 3, 3, 1));
        game.setHorizontalSequences(11, new FieldsSequences(1, 2, 3, 2, 1));
        game.setHorizontalSequences(12, new FieldsSequences(2, 2, 1, 1, 1, 1));
        game.setHorizontalSequences(13, new FieldsSequences(1, 1, 1, 2));
        game.setHorizontalSequences(14, new FieldsSequences(2, 2, 2));
        game.setHorizontalSequences(15, FieldsSequences.empty());
        return game;
    }

    @Override
    protected void addHints(@NonNull Game game) {
        // row 14
        game.setAllFieldsInRangeTo(14, 14, 1, 7, FieldState.EMPTY);
        game.setAllFieldsInRangeTo(14, 14, 8, 9, FieldState.FULL);
        game.setAllFieldsInRangeTo(14, 14, 10, 12, FieldState.EMPTY);
        game.setAllFieldsInRangeTo(14, 14, 13, 14, FieldState.FULL);
        game.setAllFieldsInRangeTo(14, 14, 15, 19, FieldState.EMPTY);
        game.setAllFieldsInRangeTo(14, 14, 20, 21, FieldState.FULL);
        game.setAllFieldsInRangeTo(14, 14, 22, 25, FieldState.EMPTY);
        // col 22
        game.setAllFieldsInRangeTo(1, 5, 22, 22, FieldState.EMPTY);
        game.setAllFieldsInRangeTo(6, 10, 22, 22, FieldState.FULL);
        game.setAllFieldsInRangeTo(11, 15, 22, 22, FieldState.EMPTY);

        game.setAllFieldsInRangeTo(13, 13, 24, 25, FieldState.FULL);
    }
}