package com.kata.tennis;

public interface TennisGamePrinter {

    String DEFAULT_SCORELINE_FORMAT = "Player 1  %s - %s  Player 2";

    default String printStringScoreLine(TennisPlayer player1, TennisPlayer player2){
        return DEFAULT_SCORELINE_FORMAT.formatted(player1.getScore(), player2.getScore());
    }

    String printGameState(TennisGame tennisGame);
}
