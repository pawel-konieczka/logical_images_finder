package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=4
 */
public class Game04Laufer extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(15, 17);
        game.setVerticalSequences(1, new FieldsSequences(3));
        game.setVerticalSequences(2, new FieldsSequences(5));
        game.setVerticalSequences(3, new FieldsSequences(7));
        game.setVerticalSequences(4, new FieldsSequences(7, 1));
        game.setVerticalSequences(5, new FieldsSequences(7, 1));
        game.setVerticalSequences(6, new FieldsSequences(3, 5, 2));
        game.setVerticalSequences(7, new FieldsSequences(5, 3, 3));
        game.setVerticalSequences(8, new FieldsSequences(7, 1, 4));
        game.setVerticalSequences(9, new FieldsSequences(15));
        game.setVerticalSequences(10, new FieldsSequences(7, 1, 4));
        game.setVerticalSequences(11, new FieldsSequences(5, 3, 3));
        game.setVerticalSequences(12, new FieldsSequences(3, 5, 2));
        game.setVerticalSequences(13, new FieldsSequences(7, 1));
        game.setVerticalSequences(14, new FieldsSequences(7, 1));
        game.setVerticalSequences(15, new FieldsSequences(7));
        game.setVerticalSequences(16, new FieldsSequences(5));
        game.setVerticalSequences(17, new FieldsSequences(3));
        game.setHorizontalSequences(1, new FieldsSequences(3));
        game.setHorizontalSequences(2, new FieldsSequences(5));
        game.setHorizontalSequences(3, new FieldsSequences(7));
        game.setHorizontalSequences(4, new FieldsSequences(7));
        game.setHorizontalSequences(5, new FieldsSequences(7));
        game.setHorizontalSequences(6, new FieldsSequences(3, 5, 3));
        game.setHorizontalSequences(7, new FieldsSequences(5, 3, 5));
        game.setHorizontalSequences(8, new FieldsSequences(7, 1, 7));
        game.setHorizontalSequences(9, new FieldsSequences(17));
        game.setHorizontalSequences(10, new FieldsSequences(7, 1, 7));
        game.setHorizontalSequences(11, new FieldsSequences(5, 1, 5));
        game.setHorizontalSequences(12, new FieldsSequences(3, 3, 3));
        game.setHorizontalSequences(13, new FieldsSequences(5));
        game.setHorizontalSequences(14, new FieldsSequences(7));
        game.setHorizontalSequences(15, new FieldsSequences(11));
        return game;
    }
}
