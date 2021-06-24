package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=18
 */
public class Game18Los extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(30, 25);
        game.setVerticalSequences(1, new FieldsSequences(1));
        game.setVerticalSequences(2, new FieldsSequences(3, 7));
        game.setVerticalSequences(3, new FieldsSequences(9, 2));
        game.setVerticalSequences(4, new FieldsSequences(8, 1));
        game.setVerticalSequences(5, new FieldsSequences(8));
        game.setVerticalSequences(6, new FieldsSequences(7));
        game.setVerticalSequences(7, new FieldsSequences(10));
        game.setVerticalSequences(8, new FieldsSequences(16));
        game.setVerticalSequences(9, new FieldsSequences(8, 2));
        game.setVerticalSequences(10, new FieldsSequences(8, 1));
        game.setVerticalSequences(11, new FieldsSequences(8));
        game.setVerticalSequences(12, new FieldsSequences(9));
        game.setVerticalSequences(13, new FieldsSequences(10));
        game.setVerticalSequences(14, new FieldsSequences(20));
        game.setVerticalSequences(15, new FieldsSequences(4, 13, 1));
        game.setVerticalSequences(16, new FieldsSequences(3, 16));
        game.setVerticalSequences(17, new FieldsSequences(6, 12, 4));
        game.setVerticalSequences(18, new FieldsSequences(6, 8, 1));
        game.setVerticalSequences(19, new FieldsSequences(8, 7));
        game.setVerticalSequences(20, new FieldsSequences(14));
        game.setVerticalSequences(21, new FieldsSequences(10, 7));
        game.setVerticalSequences(22, new FieldsSequences(1, 1, 4));
        game.setVerticalSequences(23, new FieldsSequences(8, 3));
        game.setVerticalSequences(24, new FieldsSequences(6, 3));
        game.setVerticalSequences(25, new FieldsSequences(4, 2));
        game.setHorizontalSequences(1, new FieldsSequences(1));
        game.setHorizontalSequences(2, new FieldsSequences(1, 1, 1));
        game.setHorizontalSequences(3, new FieldsSequences(1, 1, 3, 2));
        game.setHorizontalSequences(4, new FieldsSequences(1, 5, 3));
        game.setHorizontalSequences(5, new FieldsSequences(7, 3));
        game.setHorizontalSequences(6, new FieldsSequences(7, 3));
        game.setHorizontalSequences(7, new FieldsSequences(6, 3));
        game.setHorizontalSequences(8, new FieldsSequences(5, 2));
        game.setHorizontalSequences(9, new FieldsSequences(4, 1));
        game.setHorizontalSequences(10, new FieldsSequences(4, 3));
        game.setHorizontalSequences(11, new FieldsSequences(9));
        game.setHorizontalSequences(12, new FieldsSequences(13));
        game.setHorizontalSequences(13, new FieldsSequences(14, 2));
        game.setHorizontalSequences(14, new FieldsSequences(21));
        game.setHorizontalSequences(15, new FieldsSequences(1, 23));
        game.setHorizontalSequences(16, new FieldsSequences(21));
        game.setHorizontalSequences(17, new FieldsSequences(18, 2));
        game.setHorizontalSequences(18, new FieldsSequences(17, 1));
        game.setHorizontalSequences(19, new FieldsSequences(15));
        game.setHorizontalSequences(20, new FieldsSequences(7, 5));
        game.setHorizontalSequences(21, new FieldsSequences(3, 2, 4));
        game.setHorizontalSequences(22, new FieldsSequences(3, 2, 3));
        game.setHorizontalSequences(23, new FieldsSequences(2, 2, 1, 1));
        game.setHorizontalSequences(24, new FieldsSequences(1, 1, 1, 1));
        game.setHorizontalSequences(25, new FieldsSequences(1, 1, 1, 1));
        game.setHorizontalSequences(26, new FieldsSequences(1, 1, 1, 1));
        game.setHorizontalSequences(27, new FieldsSequences(1, 1, 1, 1));
        game.setHorizontalSequences(28, new FieldsSequences(1, 2, 1, 1));
        game.setHorizontalSequences(29, new FieldsSequences(1, 2, 1, 2));
        game.setHorizontalSequences(30, new FieldsSequences(2, 1));
        return game;
    }
}