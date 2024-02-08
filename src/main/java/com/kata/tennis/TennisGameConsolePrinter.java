package com.kata.tennis;

public class TennisGameConsolePrinter implements TennisGamePrinter {

    @Override
    public String printGameState(TennisGame tennisGame) {

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
            state = printStringScoreLine(player1, player2);
        }
        System.out.println(state);
        return state;
    }

}
