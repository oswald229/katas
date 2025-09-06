package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

record OldSet(LinkedList<Game> games) {
    public OldSet() {
        this(new LinkedList<>());
    }

    TennisPlayer lead() {
        return Optional.ofNullable(lastGame())
                .map(Game::winner)
                .orElse(TennisPlayer.EMPTY_PLAYER);
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public TennisPlayer lastGameWinner() {
        return lastGame().winner();
    }

    private Game lastGame() {
        return Optional.ofNullable(games.peekLast()).orElseThrow();
    }

    public TennisPlayer lastGameLoser() {
        return lastGame().looser();
    }
}
