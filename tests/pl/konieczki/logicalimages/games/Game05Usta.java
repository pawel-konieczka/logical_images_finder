package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=5
 */
public class Game05Usta extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(20, 25);
        game.setVerticalSequences(1, FieldsSequences.empty());
        game.setVerticalSequences(2, FieldsSequences.empty());
        game.setVerticalSequences(3, new FieldsSequences(5));
        game.setVerticalSequences(4, new FieldsSequences(12));
        game.setVerticalSequences(5, new FieldsSequences(3, 9));
        game.setVerticalSequences(6, new FieldsSequences(3, 6, 4));
        game.setVerticalSequences(7, new FieldsSequences(6, 1, 3));
        game.setVerticalSequences(8, new FieldsSequences(5, 3, 2));
        game.setVerticalSequences(9, new FieldsSequences(5, 3, 3));
        game.setVerticalSequences(10, new FieldsSequences(2, 2, 3, 4));
        game.setVerticalSequences(11, new FieldsSequences(5, 3, 3));
        game.setVerticalSequences(12, new FieldsSequences(5, 3, 3));
        game.setVerticalSequences(13, new FieldsSequences(5, 3, 4));
        game.setVerticalSequences(14, new FieldsSequences(5, 2, 4));
        game.setVerticalSequences(15, new FieldsSequences(5, 8));
        game.setVerticalSequences(16, new FieldsSequences(4, 7));
        game.setVerticalSequences(17, new FieldsSequences(5, 7));
        game.setVerticalSequences(18, new FieldsSequences(4, 6));
        game.setVerticalSequences(19, new FieldsSequences(4, 6));
        game.setVerticalSequences(20, new FieldsSequences(9));
        game.setVerticalSequences(21, new FieldsSequences(7));
        game.setVerticalSequences(22, new FieldsSequences(4));
        game.setVerticalSequences(23, new FieldsSequences(3));
        game.setVerticalSequences(24, FieldsSequences.empty());
        game.setVerticalSequences(25, FieldsSequences.empty());
        game.setHorizontalSequences(1, FieldsSequences.empty());
        game.setHorizontalSequences(2, FieldsSequences.empty());
        game.setHorizontalSequences(3, new FieldsSequences(6));
        game.setHorizontalSequences(4, new FieldsSequences(9));
        game.setHorizontalSequences(5, new FieldsSequences(5, 9));
        game.setHorizontalSequences(6, new FieldsSequences(17));
        game.setHorizontalSequences(7, new FieldsSequences(18));
        game.setHorizontalSequences(8, new FieldsSequences(2, 3, 7));
        game.setHorizontalSequences(9, new FieldsSequences(2, 3, 4));
        game.setHorizontalSequences(10, new FieldsSequences(5, 9));
        game.setHorizontalSequences(11, new FieldsSequences(4, 12));
        game.setHorizontalSequences(12, new FieldsSequences(4, 14));
        game.setHorizontalSequences(13, new FieldsSequences(10, 7));
        game.setHorizontalSequences(14, new FieldsSequences(3, 3, 7));
        game.setHorizontalSequences(15, new FieldsSequences(2, 7));
        game.setHorizontalSequences(16, new FieldsSequences(3, 8));
        game.setHorizontalSequences(17, new FieldsSequences(4, 7));
        game.setHorizontalSequences(18, new FieldsSequences(9));
        game.setHorizontalSequences(19, new FieldsSequences(5));
        game.setHorizontalSequences(20, FieldsSequences.empty());
        return game;
    }
}
