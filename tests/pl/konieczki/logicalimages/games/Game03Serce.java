package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=3
 */
public class Game03Serce extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(10, 11);
        game.setHorizontalSequences(1, new FieldsSequences(2, 2));
        game.setHorizontalSequences(2, new FieldsSequences(4, 4));
        game.setHorizontalSequences(3, new FieldsSequences(11));
        game.setHorizontalSequences(4, new FieldsSequences(11));
        game.setHorizontalSequences(5, new FieldsSequences(11));
        game.setHorizontalSequences(6, new FieldsSequences(9));
        game.setHorizontalSequences(7, new FieldsSequences(7));
        game.setHorizontalSequences(8, new FieldsSequences(5));
        game.setHorizontalSequences(9, new FieldsSequences(3));
        game.setHorizontalSequences(10, new FieldsSequences(1));
        game.setVerticalSequences(1, new FieldsSequences(3));
        game.setVerticalSequences(2, new FieldsSequences(5));
        game.setVerticalSequences(3, new FieldsSequences(7));
        game.setVerticalSequences(4, new FieldsSequences(8));
        game.setVerticalSequences(5, new FieldsSequences(8));
        game.setVerticalSequences(6, new FieldsSequences(8));
        game.setVerticalSequences(7, new FieldsSequences(8));
        game.setVerticalSequences(8, new FieldsSequences(8));
        game.setVerticalSequences(9, new FieldsSequences(7));
        game.setVerticalSequences(10, new FieldsSequences(5));
        game.setVerticalSequences(11, new FieldsSequences(3));

        return game;
    }
}