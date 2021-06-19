package pl.konieczki.logicalimages.games;

import pl.konieczki.logicalimages.model.FieldsSequences;
import pl.konieczki.logicalimages.model.Game;

public class Game06Pantofelek extends AbstractTestGame {

    @Override
    protected Game prepareGame() {
        final Game game = new Game(20, 20);
        game.setVerticalSequences(1, new FieldsSequences(7));
        game.setVerticalSequences(2, new FieldsSequences(2, 13));
        game.setVerticalSequences(3, new FieldsSequences(2, 3, 3));
        game.setVerticalSequences(4, new FieldsSequences(8, 2));
        game.setVerticalSequences(5, new FieldsSequences(11));
        game.setVerticalSequences(6, new FieldsSequences(11));
        game.setVerticalSequences(7, new FieldsSequences(11));
        game.setVerticalSequences(8, new FieldsSequences(13));
        game.setVerticalSequences(9, new FieldsSequences(10));
        game.setVerticalSequences(10, new FieldsSequences(3, 4));
        game.setVerticalSequences(11, new FieldsSequences(2, 2));
        game.setVerticalSequences(12, new FieldsSequences(1, 2));
        game.setVerticalSequences(13, new FieldsSequences(2, 2));
        game.setVerticalSequences(14, new FieldsSequences(2, 1));
        game.setVerticalSequences(15, new FieldsSequences(2, 1));
        game.setVerticalSequences(16, new FieldsSequences(2, 1));
        game.setVerticalSequences(17, new FieldsSequences(1, 1));
        game.setVerticalSequences(18, new FieldsSequences(1, 1));
        game.setVerticalSequences(19, new FieldsSequences(3));
        game.setVerticalSequences(20, new FieldsSequences(2));
        game.setHorizontalSequences(1, new FieldsSequences(1));
        game.setHorizontalSequences(2, new FieldsSequences(3));
        game.setHorizontalSequences(3, new FieldsSequences(5));
        game.setHorizontalSequences(4, new FieldsSequences(2, 3));
        game.setHorizontalSequences(5, new FieldsSequences(1, 5));
        game.setHorizontalSequences(6, new FieldsSequences(1, 5));
        game.setHorizontalSequences(7, new FieldsSequences(1, 6));
        game.setHorizontalSequences(8, new FieldsSequences(2, 5));
        game.setHorizontalSequences(9, new FieldsSequences(3, 4));
        game.setHorizontalSequences(10, new FieldsSequences(8));
        game.setHorizontalSequences(11, new FieldsSequences(8));
        game.setHorizontalSequences(12, new FieldsSequences(1, 5));
        game.setHorizontalSequences(13, new FieldsSequences(1, 5));
        game.setHorizontalSequences(14, new FieldsSequences(1, 4));
        game.setHorizontalSequences(15, new FieldsSequences(1, 5));
        game.setHorizontalSequences(16, new FieldsSequences(1, 2, 3));
        game.setHorizontalSequences(17, new FieldsSequences(1, 3, 4));
        game.setHorizontalSequences(18, new FieldsSequences(1, 3, 6));
        game.setHorizontalSequences(19, new FieldsSequences(1, 6, 2));
        game.setHorizontalSequences(20, new FieldsSequences(1, 12));
        return game;
    }
}
