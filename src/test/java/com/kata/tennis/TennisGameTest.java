package com.kata.tennis;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameTest {


    @Test
    void should_increase_round_winner_score() {
        TennisPlayer player1 = new TennisPlayer();
        TennisGame tennisGame = new TennisGame(player1, new TennisPlayer());
        mockRandomizer(tennisGame, true);

        tennisGame.playRound();

        assertEquals(TennisScore.FIFTEEN, player1.getScore());
    }

    @Test
    void should_set_game_back_to_deuces() {
        TennisPlayer player1 = new TennisPlayer(TennisScore.AV);
        TennisPlayer player2 = new TennisPlayer(TennisScore.FORTY);
        TennisGame tennisGame = new TennisGame(player1, player2);
        tennisGame.setAdvantage(player1);
        mockRandomizer(tennisGame, false);

        tennisGame.playRound();

        assertEquals(TennisScore.FORTY, player1.getScore());
        assertEquals(TennisScore.FORTY, player2.getScore());

    }

    @Test
    void should_set_game_back_to_deuces_bis() {
        TennisPlayer player1 = new TennisPlayer(TennisScore.FORTY);
        TennisPlayer player2 = new TennisPlayer(TennisScore.AV);
        TennisGame tennisGame = new TennisGame(player1, player2);
        tennisGame.setAdvantage(player2);

        mockRandomizer(tennisGame, true);

        tennisGame.playRound();

        assertEquals(TennisScore.FORTY, player1.getScore());
        assertEquals(TennisScore.FORTY, player2.getScore());

    }

    @Test
    void should_give_advantage() {
        TennisPlayer player1 = new TennisPlayer(TennisScore.FORTY);
        TennisPlayer player2 = new TennisPlayer(TennisScore.FORTY);
        TennisGame tennisGame = new TennisGame(player1, player2);

        mockRandomizer(tennisGame, true);

        tennisGame.playRound();

        assertEquals(player1.getScore(), TennisScore.AV);
    }

    @Test
    void should_throw_on_game_winner() {
        TennisPlayer player1 = new TennisPlayer("Player 1", TennisScore.FORTY);
        TennisPlayer player2 = new TennisPlayer(TennisScore.LOVE);
        TennisGame tennisGame = new TennisGame(player1, player2);

        mockRandomizer(tennisGame, true);

        GameWinnerException gameWinnerException = assertThrows(GameWinnerException.class, tennisGame::playRound);
        assertEquals("Player 1 Wins !", gameWinnerException.getMessage());
    }

    @Test
    void should_win_on_advantage() {
        TennisPlayer player1 = new TennisPlayer(TennisScore.FORTY);
        TennisPlayer player2 = new TennisPlayer("Player 2", TennisScore.FORTY);
        TennisGame tennisGame = new TennisGame(player1, player2);
        tennisGame.setAdvantage(player2);

        mockRandomizer(tennisGame, false);

        GameWinnerException gameWinnerException = assertThrows(GameWinnerException.class, tennisGame::playRound);
        assertEquals("Player 2 Wins !", gameWinnerException.getMessage());
    }

    @Test
    void should_throw_when_max_round_number_has_been_reached() {
        TennisGame tennisGame = new TennisGame();
        assertThrows(MaxRoundNumberReached.class, () -> tennisGame.play(1));
    }

    @Nested
    class ScoreLinePrinting {


        @Test
        void should_print_current_score() {
            TennisPlayer player1 = new TennisPlayer("Player 1", TennisScore.FORTY);
            TennisPlayer player2 = new TennisPlayer("Player 2", TennisScore.THIRTY);

            TennisGame tennisGame = new TennisGame(player1, player2);
            String expected = "Player 1  40 - 30  Player 2";

            String output = tennisGame.toString();

            assertEquals(expected, output);
        }

        @Test
        void shouldPrintAdvantage() {
            TennisPlayer player1 = new TennisPlayer("Player 1", TennisScore.FORTY);
            TennisPlayer player2 = new TennisPlayer("Player 2", TennisScore.FORTY);
            TennisGame tennisGame = new TennisGame(player1, player2);

            mockRandomizer(tennisGame, true);

            tennisGame.playRound();


            String expected = "(*) Player 1  40 - 40  Player 2";

            assertEquals(expected, tennisGame.toString());
        }

        @Test
        void shouldPrintAdvantageBis() {
            TennisPlayer player1 = new TennisPlayer("Player 1", TennisScore.FORTY);
            TennisPlayer player2 = new TennisPlayer("Player 2", TennisScore.FORTY);
            TennisGame tennisGame = new TennisGame(player1, player2);

            mockRandomizer(tennisGame, false);

            tennisGame.playRound();
            String expected = "Player 1  40 - 40  Player 2 (*)";

            String output = new TennisGameConsolePrinter(tennisGame).output();
            assertEquals(expected, output);
        }

    }

    private static void mockRandomizer(TennisGame tennisGame, boolean player1Wins) {
        try {

            Field randomizer = Arrays.stream(tennisGame.getClass().getDeclaredFields())
                    .filter(field -> field.getName().equals("randomizer"))
                    .findFirst()
                    .orElseThrow();
            randomizer.setAccessible(true);
            randomizer.set(tennisGame, new MyRandomizer(player1Wins));
            randomizer.setAccessible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static class MyRandomizer extends Random {

        private final boolean player1Wins;

        public MyRandomizer(boolean player1Wins) {
            this.player1Wins = player1Wins;
        }

        @Override
        public boolean nextBoolean() {
            return player1Wins;
        }
    }
}
