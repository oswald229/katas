package com.kata.tennis;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TennisGamePrinterTest {
    @Test
    void should_print_current_score() {
        TennisPlayer player1 = new TennisPlayer();
        TennisPlayer player2 = new TennisPlayer();
        player1.setScore(TennisScore.FORTY);
        player2.setScore(TennisScore.THIRTY);

        String expected = "Player 1  40 - 30  Player 2";
        TennisGamePrinter spy = Mockito.spy(TennisGamePrinter.class);

        String output = spy.printStringScoreLine(player1, player2);

        assertEquals(expected, output);
    }

}
