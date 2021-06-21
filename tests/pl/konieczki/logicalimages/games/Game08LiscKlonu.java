package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=8
 */
public class Game08LiscKlonu extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(25, 25);
        game.setVerticalSequences(1, new FieldsSequences(2));
        game.setVerticalSequences(2, new FieldsSequences(2, 2));
        game.setVerticalSequences(3, new FieldsSequences(3, 3));
        game.setVerticalSequences(4, new FieldsSequences(8));
        game.setVerticalSequences(5, new FieldsSequences(8));
        game.setVerticalSequences(6, new FieldsSequences(5, 5, 2));
        game.setVerticalSequences(7, new FieldsSequences(5, 8));
        game.setVerticalSequences(8, new FieldsSequences(2, 4, 7));
        game.setVerticalSequences(9, new FieldsSequences(3, 4, 5));
        game.setVerticalSequences(10, new FieldsSequences(6, 5, 4));
        game.setVerticalSequences(11, new FieldsSequences(13, 2));
        game.setVerticalSequences(12, new FieldsSequences(17, 1));
        game.setVerticalSequences(13, new FieldsSequences(5, 1, 1, 1, 1, 1, 1, 7));
        game.setVerticalSequences(14, new FieldsSequences(17, 1));
        game.setVerticalSequences(15, new FieldsSequences(13, 2));
        game.setVerticalSequences(16, new FieldsSequences(6, 5, 4));
        game.setVerticalSequences(17, new FieldsSequences(3, 4, 5));
        game.setVerticalSequences(18, new FieldsSequences(2, 4, 7));
        game.setVerticalSequences(19, new FieldsSequences(5, 8));
        game.setVerticalSequences(20, new FieldsSequences(5, 5, 2));
        game.setVerticalSequences(21, new FieldsSequences(8));
        game.setVerticalSequences(22, new FieldsSequences(8));
        game.setVerticalSequences(23, new FieldsSequences(3, 3));
        game.setVerticalSequences(24, new FieldsSequences(2, 2));
        game.setVerticalSequences(25, new FieldsSequences(2));
        game.setHorizontalSequences(1, new FieldsSequences(1));
        game.setHorizontalSequences(2, new FieldsSequences(3));
        game.setHorizontalSequences(3, new FieldsSequences(1, 3, 1));
        game.setHorizontalSequences(4, new FieldsSequences(3, 3, 3));
        game.setHorizontalSequences(5, new FieldsSequences(9));
        game.setHorizontalSequences(6, new FieldsSequences(4, 4));
        game.setHorizontalSequences(7, new FieldsSequences(7));
        game.setHorizontalSequences(8, new FieldsSequences(1, 1, 3, 3, 1, 1));
        game.setHorizontalSequences(9, new FieldsSequences(3, 2, 7, 2, 3));
        game.setHorizontalSequences(10, new FieldsSequences(6, 2, 2, 6));
        game.setHorizontalSequences(11, new FieldsSequences(6, 5, 6));
        game.setHorizontalSequences(12, new FieldsSequences(9, 9));
        game.setHorizontalSequences(13, new FieldsSequences(2, 13, 2));
        game.setHorizontalSequences(14, new FieldsSequences(5, 5, 5, 5));
        game.setHorizontalSequences(15, new FieldsSequences(6, 9, 6));
        game.setHorizontalSequences(16, new FieldsSequences(6, 3, 3, 6));
        game.setHorizontalSequences(17, new FieldsSequences(6, 5, 6));
        game.setHorizontalSequences(18, new FieldsSequences(5, 1, 1, 5));
        game.setHorizontalSequences(19, new FieldsSequences(5, 1, 5));
        game.setHorizontalSequences(20, new FieldsSequences(13));
        game.setHorizontalSequences(21, new FieldsSequences(5, 1, 5));
        game.setHorizontalSequences(22, new FieldsSequences(3, 1, 3));
        game.setHorizontalSequences(23, new FieldsSequences(1));
        game.setHorizontalSequences(24, new FieldsSequences(1));
        game.setHorizontalSequences(25, new FieldsSequences(1));
        return game;
    }
}
