package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=26
 */
public class Game26Kaktus extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(25, 15);
        game.setVerticalSequences(1, new FieldsSequences(5, 7));
        game.setVerticalSequences(2, new FieldsSequences(15));
        game.setVerticalSequences(3, new FieldsSequences(5, 8));
        game.setVerticalSequences(4, new FieldsSequences(3, 1));
        game.setVerticalSequences(5, new FieldsSequences(2, 1));
        game.setVerticalSequences(6, new FieldsSequences(2, 1));
        game.setVerticalSequences(7, new FieldsSequences(11, 11));
        game.setVerticalSequences(8, new FieldsSequences(24));
        game.setVerticalSequences(9, new FieldsSequences(24));
        game.setVerticalSequences(10, new FieldsSequences(11, 11));
        game.setVerticalSequences(11, new FieldsSequences(2, 2, 1));
        game.setVerticalSequences(12, new FieldsSequences(6, 3, 1));
        game.setVerticalSequences(13, new FieldsSequences(5, 7, 1));
        game.setVerticalSequences(14, new FieldsSequences(8));
        game.setVerticalSequences(15, new FieldsSequences(6));
        game.setHorizontalSequences(1, FieldsSequences.empty());
        game.setHorizontalSequences(2, new FieldsSequences(2));
        game.setHorizontalSequences(3, new FieldsSequences(4, 2));
        game.setHorizontalSequences(4, new FieldsSequences(4, 2));
        game.setHorizontalSequences(5, new FieldsSequences(4, 2));
        game.setHorizontalSequences(6, new FieldsSequences(1, 4, 2));
        game.setHorizontalSequences(7, new FieldsSequences(3, 7));
        game.setHorizontalSequences(8, new FieldsSequences(3, 6));
        game.setHorizontalSequences(9, new FieldsSequences(3, 4));
        game.setHorizontalSequences(10, new FieldsSequences(3, 4));
        game.setHorizontalSequences(11, new FieldsSequences(3, 4, 1));
        game.setHorizontalSequences(12, new FieldsSequences(1, 4, 3));
        game.setHorizontalSequences(13, new FieldsSequences(3, 4, 3));
        game.setHorizontalSequences(14, new FieldsSequences(3, 2, 3));
        game.setHorizontalSequences(15, new FieldsSequences(3, 4, 3));
        game.setHorizontalSequences(16, new FieldsSequences(3, 4, 4));
        game.setHorizontalSequences(17, new FieldsSequences(3, 9));
        game.setHorizontalSequences(18, new FieldsSequences(4, 8));
        game.setHorizontalSequences(19, new FieldsSequences(10));
        game.setHorizontalSequences(20, new FieldsSequences(9));
        game.setHorizontalSequences(21, new FieldsSequences(4));
        game.setHorizontalSequences(22, new FieldsSequences(4));
        game.setHorizontalSequences(23, new FieldsSequences(4));
        game.setHorizontalSequences(24, new FieldsSequences(4));
        game.setHorizontalSequences(25, new FieldsSequences(10));
        return game;
    }
}
