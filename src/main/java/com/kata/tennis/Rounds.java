package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

record Rounds(LinkedList<TennisPlayer> rounds,
              LinkedList<Game> games) {
    public Rounds() {
        this(new LinkedList<>(), new LinkedList<>());
    }

    void addWinner(TennisPlayer tennisPlayer) {
        this.rounds.add(tennisPlayer);
    }

    TennisPlayer lead(){
        return Optional.ofNullable(rounds.peekLast()).orElse(TennisPlayer.EMPTY_PLAYER);
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
