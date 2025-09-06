package com.kata.tennis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TennisGamePrinterTest {
    @Test
    void should_scoreLine_current_score() {
        TennisPlayer player1 = new TennisPlayer();
        TennisPlayer player2 = new TennisPlayer();
        player1.setScore(TennisScore.FORTY);
        player2.setScore(TennisScore.THIRTY);

        String expected = "Player 1  40 - 30  Player 2";

        String output = ((TennisGamePrinter) tennisGame -> "").scoreLine(player1, player2);

        assertEquals(expected, output);
    }

}
