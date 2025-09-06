package com.kata.tennis;

import java.util.function.Supplier;

record DefaultScoreLine(TennisPlayer player1, TennisPlayer player2, Supplier<TennisPlayer> advantage) {

    @Override
    public String toString() {
        if (advantage.get().equals(TennisPlayer.EMPTY_PLAYER)) {
            return "Player 1  %s - %s  Player 2".formatted(player1.getScore(), player2.getScore());
        }
        if (advantage.get().equals(player1)) {
            return ("(*) " + "Player 1  %s - %s  Player 2").formatted(player1.getScore(), player2.getScore());
        }
        return ("Player 1  %s - %s  Player 2" + " (*)").formatted(player1.getScore(), player2.getScore());
    }
}
