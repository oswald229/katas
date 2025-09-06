package com.kata.tennis;

import java.io.PrintStream;

public interface TennisGamePrinter {
    String print(PrintStream out);

    record ScoreLineOutput(TennisPlayer player1, TennisPlayer player2) {
        @Override
        public String toString() {
            return "Player 1  %s - %s  Player 2".formatted(player1.getScore(), player2.getScore());
        }
    }

    default String scoreLine(TennisPlayer player1, TennisPlayer player2) {
        return new ScoreLineOutput(player1, player2).toString();
    }

    default String print() {
        return print(System.out);
    }
}
