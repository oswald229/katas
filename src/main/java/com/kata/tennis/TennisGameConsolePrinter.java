package com.kata.tennis;

import java.io.PrintStream;

public class TennisGameConsolePrinter implements TennisGamePrinter {
    private final TennisGame tennisGame;
    private final String DEFAULT_SCORELINE_FORMAT;

    public TennisGameConsolePrinter(TennisGame tennisGame) {
        this(tennisGame, "Player 1  %s - %s  Player 2");
    }

    public TennisGameConsolePrinter(TennisGame tennisGame, String scorelineFormat) {
        this.tennisGame = tennisGame;
        this.DEFAULT_SCORELINE_FORMAT = scorelineFormat;
    }

    @Override
    public String print(PrintStream out) {

        TennisPlayer player1 = tennisGame.getPlayer1();
        TennisPlayer player2 = tennisGame.getPlayer2();
        String state;
        if (tennisGame.advantage != null) {
            if (tennisGame.advantage.equals(player1)) {
                state = ("(*) " + DEFAULT_SCORELINE_FORMAT).formatted(player1.getScore(), player2.getScore());
            } else {
                state = (DEFAULT_SCORELINE_FORMAT + " (*)").formatted(player1.getScore(), player2.getScore());
            }

        } else {
            state = scoreLine(player1, player2);
        }
        out.println(state);
        return state;
    }

}
