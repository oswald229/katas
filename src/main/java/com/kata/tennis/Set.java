package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

class Set {

    private final LinkedList<Game> games;

    Set(LinkedList<Game> games) {
        this.games = games;
    }

    public Optional<TennisPlayer> winner() {
        return Optional.ofNullable(games.peekLast())
                .filter(Game::wasSetWinning)
                .map(Game::winner);
    }

}
