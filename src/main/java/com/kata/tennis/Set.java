package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

class Set {

    private final LinkedList<Game> games;

    Set() {
        this.games = new LinkedList<>();
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public Optional<TennisPlayer> winner() {
        Game lastGame = lastGame();
        if (lastGame.wasSetWinning()) {
            return Optional.of(lastGame.winner());
        }
        return Optional.empty();
    }

    private Game lastGame() {
        return this.games.peekLast();
    }

}
