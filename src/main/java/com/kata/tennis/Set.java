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
        return Optional.ofNullable(games.peekLast())
                .filter(Game::wasSetWinning)
                .map(Game::winner);
    }

}
