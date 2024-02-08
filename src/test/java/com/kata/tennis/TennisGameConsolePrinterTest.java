package com.kata.tennis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TennisGameConsolePrinterTest {

    private final TennisGameConsolePrinter printer = new TennisGameConsolePrinter();

    @Test
    void shouldPrintAdvantage() {

        TennisGame tennisGame = new TennisGame();
        TennisPlayer player1 = tennisGame.getPlayer1();
        player1.setScore(TennisScore.FORTY);
        tennisGame.getPlayer2().setScore(TennisScore.FORTY);

        tennisGame.advantage = player1;
        String expected = "(*) Player 1  40 - 40  Player 2";

        String output = printer.printGameState(tennisGame);
        assertEquals(expected, output);
    }

    @Test
    void shouldPrintAdvantageBis() {

        TennisGame tennisGame = new TennisGame();
        tennisGame.getPlayer1().setScore(TennisScore.FORTY);
        TennisPlayer player2 = tennisGame.getPlayer2();
        player2.setScore(TennisScore.FORTY);

        tennisGame.advantage = player2;
        String expected = "Player 1  40 - 40  Player 2 (*)";

        String output = printer.printGameState(tennisGame);
        assertEquals(expected, output);
    }

}