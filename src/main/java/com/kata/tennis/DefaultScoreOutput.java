package com.kata.tennis;

import java.util.function.Supplier;

record DefaultScoreOutput(TennisPlayer player1, TennisPlayer player2, Supplier<TennisPlayer> advantage) {

    @Override
    public String toString() {
        if (advantage.get().equals(TennisPlayer.EMPTY_PLAYER)) {
            return "%s  %s - %s  %s".formatted(player1, player1.getScore(), player2.getScore(), player2);
        }
        if (advantage.get().equals(player1)) {
            return ("(*) " + "%s  %s - %s  %s").formatted(
                    player1, player1.getScore(),
                    player2.getScore(), player2);
        }
        return ("%s  %s - %s  %s" + " (*)")
                .formatted(player1, player1.getScore(),
                        player2.getScore(), player2);
    }
}
