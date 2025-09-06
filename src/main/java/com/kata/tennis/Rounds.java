package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

record Rounds(LinkedList<TennisPlayer> rounds) {
    public Rounds() {
        this(new LinkedList<>());
    }

    void addWinner(TennisPlayer tennisPlayer) {
        this.rounds.add(tennisPlayer);
    }

    TennisPlayer lead(){
        return Optional.ofNullable(rounds.peekLast()).orElse(TennisPlayer.EMPTY_PLAYER);
    }
}
