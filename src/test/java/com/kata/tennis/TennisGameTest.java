package com.kata.tennis;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameTest {
    @Test
    void should_init_game() {

        TennisGame tennisGame = new TennisGame();

        assertInstanceOf(TennisPlayer.class, tennisGame.getPlayer1());
        assertInstanceOf(TennisPlayer.class, tennisGame.getPlayer2());
        assertNotNull(tennisGame.getPlayer1());
        assertNotNull(tennisGame.getPlayer2());
    }


    @Test
    void should_tell_round_winner() {
        TennisGame tennisGame = new TennisGame();
        TennisPlayer roundWinner = tennisGame.playRound();

        TennisPlayer tennisPlayer1 = tennisGame.getPlayer1();
        TennisPlayer tennisPlayer2 = tennisGame.getPlayer2();

        boolean aPlayerWon = tennisPlayer1.equals(roundWinner) || tennisPlayer2.equals(roundWinner);
        assertTrue(aPlayerWon);

    }

    @Test
    void should_increase_round_winner_score() {
        TennisGame tennisGame = new TennisGame();

        TennisPlayer roundWinner = tennisGame.playRound();

        TennisScore roundWinnerScore = roundWinner.getScore();

        assertEquals(TennisScore.FIFTEEN, roundWinnerScore);

    }

    @Test
    void should_set_game_back_to_deuces() {
        TennisPlayer player1 = new TennisPlayer(TennisScore.FORTY);
        TennisPlayer player2 = new TennisPlayer(TennisScore.FORTY);
        TennisGame tennisGame = new TennisGame(player1, player2);
        tennisGame.setAdvantage(player1);
        mockRandomizer(tennisGame, false);

        tennisGame.playRound();

        assertEquals(TennisScore.FORTY, player1.getScore());
        assertEquals(TennisScore.FORTY, player2.getScore());
        assertEquals(TennisPlayer.EMPTY_PLAYER, tennisGame.advantage());

    }

    @Test
    void should_set_game_back_to_deuces_bis() {
        TennisPlayer player1 = new TennisPlayer(TennisScore.FORTY);
        TennisPlayer player2 = new TennisPlayer(TennisScore.FORTY);
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

        assertEquals(player1, tennisGame.advantage());
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
        assertThrows(MaxRoundNumberReached.class, () -> tennisGame.playGame(1));
    }

    @Nested
    class ScoreLinePrinting {


        @Test
        void should_print_current_score() {
            TennisPlayer player1 = new TennisPlayer("Player 1", new PlayerScore());
            player1.setScore(TennisScore.FORTY);
            TennisPlayer player2 = new TennisPlayer("Player 2", new PlayerScore());
            player2.setScore(TennisScore.THIRTY);

            TennisGame tennisGame = new TennisGame(player1, player2);
            String expected = "Player 1  40 - 30  Player 2";

            String output = tennisGame.toString();

            assertEquals(expected, output);
        }

        @Test
        void shouldPrintAdvantage() {

            TennisGame tennisGame = new TennisGame();
            TennisPlayer player1 = tennisGame.getPlayer1();
            player1.setScore(TennisScore.FORTY);
            TennisPlayer player2 = tennisGame.getPlayer2();
            player2.setScore(TennisScore.FORTY);

            mockRandomizer(tennisGame, true);

            tennisGame.playRound();


            String expected = "(*) Player 1  40 - 40  Player 2";

            assertEquals(expected, tennisGame.toString());
        }

        @Test
        void shouldPrintAdvantageBis() {

            TennisGame tennisGame = new TennisGame();
            TennisPlayer player1 = tennisGame.getPlayer1();
            player1.setScore(TennisScore.FORTY);
            TennisPlayer player2 = tennisGame.getPlayer2();
            player2.setScore(TennisScore.FORTY);

            mockRandomizer(tennisGame, false);

            tennisGame.playRound();
            String expected = "Player 1  40 - 40  Player 2 (*)";

            String output = new TennisGameConsolePrinter(tennisGame).print();
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
