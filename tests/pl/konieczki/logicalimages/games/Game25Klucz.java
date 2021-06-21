package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=25
 */
public class Game25Klucz extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(10, 25);

        game.setHorizontalSequences(1, new FieldsSequences(4));
        game.setHorizontalSequences(2, new FieldsSequences(6));
        game.setHorizontalSequences(3, new FieldsSequences(8));
        game.setHorizontalSequences(4, new FieldsSequences(23));
        game.setHorizontalSequences(5, new FieldsSequences(19, 3));

        game.setHorizontalSequences(6, new FieldsSequences(20, 3));
        game.setHorizontalSequences(7, new FieldsSequences(4, 3, 1, 13));
        game.setHorizontalSequences(8, new FieldsSequences(1, 1, 1, 1, 1, 1, 8));
        game.setHorizontalSequences(9, new FieldsSequences(6));
        game.setHorizontalSequences(10, new FieldsSequences(4));

        game.setVerticalSequences(1, new FieldsSequences(1));
        game.setVerticalSequences(2, new FieldsSequences(3));
        game.setVerticalSequences(3, new FieldsSequences(5));
        game.setVerticalSequences(4, new FieldsSequences(4));
        game.setVerticalSequences(5, new FieldsSequences(5));

        game.setVerticalSequences(6, new FieldsSequences(3));
        game.setVerticalSequences(7, new FieldsSequences(5));
        game.setVerticalSequences(8, new FieldsSequences(4));
        game.setVerticalSequences(9, new FieldsSequences(5));
        game.setVerticalSequences(10, new FieldsSequences(3));

        game.setVerticalSequences(11, new FieldsSequences(5));
        game.setVerticalSequences(12, new FieldsSequences(3));
        game.setVerticalSequences(13, new FieldsSequences(5));
        game.setVerticalSequences(14, new FieldsSequences(4));
        game.setVerticalSequences(15, new FieldsSequences(4));

        game.setVerticalSequences(16, new FieldsSequences(4));
        game.setVerticalSequences(17, new FieldsSequences(6));
        game.setVerticalSequences(18, new FieldsSequences(8));
        game.setVerticalSequences(19, new FieldsSequences(10));
        game.setVerticalSequences(20, new FieldsSequences(10));

        game.setVerticalSequences(21, new FieldsSequences(4, 4));
        game.setVerticalSequences(22, new FieldsSequences(4, 4));
        game.setVerticalSequences(23, new FieldsSequences(8));
        game.setVerticalSequences(24, new FieldsSequences(6));
        game.setVerticalSequences(25, new FieldsSequences(4));
        return game;
    }
}
