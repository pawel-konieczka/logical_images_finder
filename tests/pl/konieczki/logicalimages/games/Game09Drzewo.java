package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=9
 */
public class Game09Drzewo extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(25, 25);
        game.setVerticalSequences(1, new FieldsSequences(5, 8));
        game.setVerticalSequences(2, new FieldsSequences(7, 8));
        game.setVerticalSequences(3, new FieldsSequences(11, 8));
        game.setVerticalSequences(4, new FieldsSequences(11, 8));
        game.setVerticalSequences(5, new FieldsSequences(5, 5, 8));
        game.setVerticalSequences(6, new FieldsSequences(8, 4, 8));
        game.setVerticalSequences(7, new FieldsSequences(10, 2, 8));
        game.setVerticalSequences(8, new FieldsSequences(8, 1, 1, 7));
        game.setVerticalSequences(9, new FieldsSequences(7, 1, 1, 7));
        game.setVerticalSequences(10, new FieldsSequences(6, 3, 1, 7));
        game.setVerticalSequences(11, new FieldsSequences(8, 3, 1, 6));
        game.setVerticalSequences(12, new FieldsSequences(7, 4));
        game.setVerticalSequences(13, new FieldsSequences(8, 1));
        game.setVerticalSequences(14, new FieldsSequences(4, 4));
        game.setVerticalSequences(15, new FieldsSequences(5, 2, 1, 2, 5));
        game.setVerticalSequences(16, new FieldsSequences(7, 2, 3, 6));
        game.setVerticalSequences(17, new FieldsSequences(8, 2, 3, 7));
        game.setVerticalSequences(18, new FieldsSequences(6, 2, 3, 7));
        game.setVerticalSequences(19, new FieldsSequences(5, 3, 3, 8));
        game.setVerticalSequences(20, new FieldsSequences(8, 4, 8));
        game.setVerticalSequences(21, new FieldsSequences(5, 1, 5, 8));
        game.setVerticalSequences(22, new FieldsSequences(8, 3, 8));
        game.setVerticalSequences(23, new FieldsSequences(11, 8));
        game.setVerticalSequences(24, new FieldsSequences(9, 8));
        game.setVerticalSequences(25, new FieldsSequences(5, 8));
        game.setHorizontalSequences(1, new FieldsSequences(2, 2));
        game.setHorizontalSequences(2, new FieldsSequences(11));
        game.setHorizontalSequences(3, new FieldsSequences(2, 11));
        game.setHorizontalSequences(4, new FieldsSequences(17));
        game.setHorizontalSequences(5, new FieldsSequences(2, 18));
        game.setHorizontalSequences(6, new FieldsSequences(11, 10));
        game.setHorizontalSequences(7, new FieldsSequences(12, 3, 5));
        game.setHorizontalSequences(8, new FieldsSequences(9, 1, 3, 1, 7));
        game.setHorizontalSequences(9, new FieldsSequences(10, 3, 3, 4));
        game.setHorizontalSequences(10, new FieldsSequences(12, 1, 3, 5));
        game.setHorizontalSequences(11, new FieldsSequences(4, 2, 4, 2, 4));
        game.setHorizontalSequences(12, new FieldsSequences(5, 2, 2, 2, 3, 3));
        game.setHorizontalSequences(13, new FieldsSequences(5, 1, 1, 7));
        game.setHorizontalSequences(14, new FieldsSequences(6, 9));
        game.setHorizontalSequences(15, new FieldsSequences(8, 4, 4));
        game.setHorizontalSequences(16, new FieldsSequences(2, 1, 3, 1));
        game.setHorizontalSequences(17, FieldsSequences.empty());
        game.setHorizontalSequences(18, new FieldsSequences(11, 11));
        game.setHorizontalSequences(19, new FieldsSequences(11, 11));
        game.setHorizontalSequences(20, new FieldsSequences(11, 11));
        game.setHorizontalSequences(21, new FieldsSequences(11, 11));
        game.setHorizontalSequences(22, new FieldsSequences(11, 11));
        game.setHorizontalSequences(23, new FieldsSequences(11, 10));
        game.setHorizontalSequences(24, new FieldsSequences(10, 9));
        game.setHorizontalSequences(25, new FieldsSequences(7, 7));
        return game;
    }
}
