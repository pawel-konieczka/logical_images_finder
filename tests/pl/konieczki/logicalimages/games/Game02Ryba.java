package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=2
 */
public class Game02Ryba extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(5, 10);
        game.setHorizontalSequences(1, new FieldsSequences(1, 4));
        game.setHorizontalSequences(2, new FieldsSequences(2, 4, 1));
        game.setHorizontalSequences(3, new FieldsSequences(10));
        game.setHorizontalSequences(4, new FieldsSequences(2, 6));
        game.setHorizontalSequences(5, new FieldsSequences(1, 4));
        game.setVerticalSequences(1, new FieldsSequences(5));
        game.setVerticalSequences(2, new FieldsSequences(3));
        game.setVerticalSequences(3, new FieldsSequences(1));
        game.setVerticalSequences(4, new FieldsSequences(3));
        game.setVerticalSequences(5, new FieldsSequences(5));
        game.setVerticalSequences(6, new FieldsSequences(5));
        game.setVerticalSequences(7, new FieldsSequences(5));
        game.setVerticalSequences(8, new FieldsSequences(1, 3));
        game.setVerticalSequences(9, new FieldsSequences(3));
        game.setVerticalSequences(10, new FieldsSequences(1));

        return game;
    }
}