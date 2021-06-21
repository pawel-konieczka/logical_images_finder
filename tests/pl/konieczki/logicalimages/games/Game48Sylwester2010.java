package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=48
 */
public class Game48Sylwester2010 extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(30, 20);
        game.setVerticalSequences(1, FieldsSequences.empty());
        game.setVerticalSequences(2, FieldsSequences.empty());
        game.setVerticalSequences(3, new FieldsSequences(12, 1));
        game.setVerticalSequences(4, new FieldsSequences(1, 2, 3, 3, 5, 1));
        game.setVerticalSequences(5, new FieldsSequences(1, 1, 1, 1, 3, 3, 4, 2));
        game.setVerticalSequences(6, new FieldsSequences(2, 1, 3, 3, 3, 11));
        game.setVerticalSequences(7, new FieldsSequences(1, 3, 3, 4, 2));
        game.setVerticalSequences(8, new FieldsSequences(5, 3, 3, 5, 1));
        game.setVerticalSequences(9, new FieldsSequences(1, 1, 12, 1));
        game.setVerticalSequences(10, new FieldsSequences(5));
        game.setVerticalSequences(11, FieldsSequences.empty());
        game.setVerticalSequences(12, new FieldsSequences(1, 12, 1));
        game.setVerticalSequences(13, new FieldsSequences(5, 1, 3, 3, 3, 1));
        game.setVerticalSequences(14, new FieldsSequences(3, 3, 6, 2));
        game.setVerticalSequences(15, new FieldsSequences(5, 1, 3, 3, 1, 11));
        game.setVerticalSequences(16, new FieldsSequences(1, 1, 3, 3, 6, 2));
        game.setVerticalSequences(17, new FieldsSequences(5, 1, 3, 3, 3, 1));
        game.setVerticalSequences(18, new FieldsSequences(12, 1));
        game.setVerticalSequences(19, FieldsSequences.empty());
        game.setVerticalSequences(20, FieldsSequences.empty());
        game.setHorizontalSequences(1, new FieldsSequences(3, 3, 1, 3));
        game.setHorizontalSequences(2, new FieldsSequences(1, 1, 1, 2, 1, 1));
        game.setHorizontalSequences(3, new FieldsSequences(1, 1, 1, 1, 1, 1));
        game.setHorizontalSequences(4, new FieldsSequences(1, 1, 1, 1, 1, 1));
        game.setHorizontalSequences(5, new FieldsSequences(3, 3, 1, 3));
        game.setHorizontalSequences(6, FieldsSequences.empty());
        game.setHorizontalSequences(7, FieldsSequences.empty());
        game.setHorizontalSequences(8, new FieldsSequences(7, 7));
        game.setHorizontalSequences(9, new FieldsSequences(2, 1, 2, 1, 1, 1, 1));
        game.setHorizontalSequences(10, new FieldsSequences(7, 7));
        game.setHorizontalSequences(11, new FieldsSequences(1, 1, 1, 1, 2, 1, 2));
        game.setHorizontalSequences(12, new FieldsSequences(7, 7));
        game.setHorizontalSequences(13, new FieldsSequences(2, 1, 2, 1, 1, 1, 1));
        game.setHorizontalSequences(14, new FieldsSequences(7, 7));
        game.setHorizontalSequences(15, new FieldsSequences(1, 1, 1, 1, 2, 1, 2));
        game.setHorizontalSequences(16, new FieldsSequences(7, 7));
        game.setHorizontalSequences(17, new FieldsSequences(2, 1, 2, 1, 1, 1, 1));
        game.setHorizontalSequences(18, new FieldsSequences(7, 7));
        game.setHorizontalSequences(19, new FieldsSequences(3, 3, 3, 3));
        game.setHorizontalSequences(20, new FieldsSequences(5, 5));
        game.setHorizontalSequences(21, new FieldsSequences(3, 3));
        game.setHorizontalSequences(22, new FieldsSequences(1, 1));
        game.setHorizontalSequences(23, new FieldsSequences(1, 1));
        game.setHorizontalSequences(24, new FieldsSequences(1, 1));
        game.setHorizontalSequences(25, new FieldsSequences(1, 1));
        game.setHorizontalSequences(26, new FieldsSequences(1, 1));
        game.setHorizontalSequences(27, new FieldsSequences(1, 1));
        game.setHorizontalSequences(28, new FieldsSequences(1, 1));
        game.setHorizontalSequences(29, new FieldsSequences(3, 3));
        game.setHorizontalSequences(30, new FieldsSequences(7, 7));
        return game;
    }
}
