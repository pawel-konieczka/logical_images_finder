package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=1
 */
public class Game01LiteraN extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(5, 5);
        game.setHorizontalSequences(1, new FieldsSequences(1, 1));
        game.setHorizontalSequences(2, new FieldsSequences(2, 1));
        game.setHorizontalSequences(3, new FieldsSequences(1, 1, 1));
        game.setHorizontalSequences(4, new FieldsSequences(1, 2));
        game.setHorizontalSequences(5, new FieldsSequences(1, 1));
        game.setVerticalSequences(1, new FieldsSequences(5));
        game.setVerticalSequences(2, new FieldsSequences(1));
        game.setVerticalSequences(3, new FieldsSequences(1));
        game.setVerticalSequences(4, new FieldsSequences(1));
        game.setVerticalSequences(5, new FieldsSequences(5));

        return game;
    }
}
