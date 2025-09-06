package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

record Set(LinkedList<Game> games) {
    public Set() {
        this(new LinkedList<>());
    }

    TennisPlayer lead() {
        return Optional.ofNullable(games.peekLast())
                .map(Game::winner)
                .orElse(TennisPlayer.EMPTY_PLAYER);
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public TennisPlayer lastGameWinner() {
        return games.peekLast().winner();
    }

    public TennisPlayer lastGameLoser() {
        return games.peekLast().looser();
    }
}
