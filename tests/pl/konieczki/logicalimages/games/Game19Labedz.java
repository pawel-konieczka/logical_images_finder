package pl.konieczki.logicalimages.games;

import lombok.NonNull;
import pl.konieczki.logicalimages.model.FieldState;
import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

/**
 * Based on http://www.pazyl.pl/obrazki/obrazek.php?ol=19
 */
public class Game19Labedz extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(20, 25);
        game.setVerticalSequences(1, FieldsSequences.empty());
        game.setVerticalSequences(2, FieldsSequences.empty());
        game.setVerticalSequences(3, new FieldsSequences(1));
        game.setVerticalSequences(4, new FieldsSequences(2));
        game.setVerticalSequences(5, new FieldsSequences(3));
        game.setVerticalSequences(6, new FieldsSequences(5));
        game.setVerticalSequences(7, new FieldsSequences(6));
        game.setVerticalSequences(8, new FieldsSequences(8));
        game.setVerticalSequences(9, new FieldsSequences(8));
        game.setVerticalSequences(10, new FieldsSequences(10));
        game.setVerticalSequences(11, new FieldsSequences(13));
        game.setVerticalSequences(12, new FieldsSequences(10, 2));
        game.setVerticalSequences(13, new FieldsSequences(10, 2));
        game.setVerticalSequences(14, new FieldsSequences(11, 1));
        game.setVerticalSequences(15, new FieldsSequences(9, 2));
        game.setVerticalSequences(16, new FieldsSequences(2, 7, 2));
        game.setVerticalSequences(17, new FieldsSequences(13, 1));
        game.setVerticalSequences(18, new FieldsSequences(13));
        game.setVerticalSequences(19, new FieldsSequences(2, 8));
        game.setVerticalSequences(20, new FieldsSequences(2, 5));
        game.setVerticalSequences(21, new FieldsSequences(3, 3));
        game.setVerticalSequences(22, new FieldsSequences(3));
        game.setVerticalSequences(23, new FieldsSequences(4));
        game.setVerticalSequences(24, new FieldsSequences(3));
        game.setVerticalSequences(25, FieldsSequences.empty());
        game.setHorizontalSequences(1, FieldsSequences.empty());
        game.setHorizontalSequences(2, new FieldsSequences(4));
        game.setHorizontalSequences(3, new FieldsSequences(7));
        game.setHorizontalSequences(4, new FieldsSequences(3, 3));
        game.setHorizontalSequences(5, new FieldsSequences(3, 3));
        game.setHorizontalSequences(6, new FieldsSequences(2, 2));
        game.setHorizontalSequences(7, new FieldsSequences(4, 3, 1));
        game.setHorizontalSequences(8, new FieldsSequences(8, 3));
        game.setHorizontalSequences(9, new FieldsSequences(14));
        game.setHorizontalSequences(10, new FieldsSequences(16));
        game.setHorizontalSequences(11, new FieldsSequences(17));
        game.setHorizontalSequences(12, new FieldsSequences(18));
        game.setHorizontalSequences(13, new FieldsSequences(18));
        game.setHorizontalSequences(14, new FieldsSequences(14));
        game.setHorizontalSequences(15, new FieldsSequences(10));
        game.setHorizontalSequences(16, new FieldsSequences(6));
        game.setHorizontalSequences(17, new FieldsSequences(1, 1));
        game.setHorizontalSequences(18, new FieldsSequences(1, 3));
        game.setHorizontalSequences(19, new FieldsSequences(3, 3));
        game.setHorizontalSequences(20, new FieldsSequences(3));
        return game;
    }

    @Override
    protected void addHints(@NonNull Game game) {
        game.setField(9, 20, FieldState.FULL); // rozwiązanie jest niejednoznaczne; dodaje hinta
    }
}
