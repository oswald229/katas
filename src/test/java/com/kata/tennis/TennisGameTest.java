package com.kata.tennis;

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
    void should_set_game_back_to_deuces() throws IllegalAccessException {
        TennisGame tennisGame = new TennisGame();
        TennisPlayer player1 = tennisGame.getPlayer1();
        player1.setScore(TennisScore.FORTY);
        TennisPlayer player2 = tennisGame.getPlayer2();
        player2.setScore(TennisScore.FORTY);
        tennisGame.advantage = player1;

        mockRandomizer(tennisGame, false);

        tennisGame.playRound();

        assertEquals(TennisScore.FORTY, player1.getScore());
        assertEquals(TennisScore.FORTY, player2.getScore());
        assertNull(tennisGame.advantage);

    }

    @Test
    void should_set_game_back_to_deuces_bis() throws IllegalAccessException {
        TennisGame tennisGame = new TennisGame();
        TennisPlayer player1 = tennisGame.getPlayer1();
        player1.setScore(TennisScore.FORTY);
        TennisPlayer player2 = tennisGame.getPlayer2();
        player2.setScore(TennisScore.FORTY);
        tennisGame.advantage = player2;

        mockRandomizer(tennisGame, true);

        tennisGame.playRound();

        assertEquals(TennisScore.FORTY, player1.getScore());
        assertEquals(TennisScore.FORTY, player2.getScore());

    }

    @Test
    void should_give_advantage() throws IllegalAccessException {
        TennisGame tennisGame = new TennisGame();
        TennisPlayer player1 = tennisGame.getPlayer1();
        player1.setScore(TennisScore.FORTY);
        TennisPlayer player2 = tennisGame.getPlayer2();
        player2.setScore(TennisScore.FORTY);

        mockRandomizer(tennisGame, true);

        tennisGame.playRound();

        assertEquals(player1, tennisGame.advantage);
    }

    @Test
    void should_throw_on_game_winner() throws IllegalAccessException {
        TennisGame tennisGame = new TennisGame();
        TennisPlayer player1 = tennisGame.getPlayer1();
        player1.setScore(TennisScore.FORTY);
        TennisPlayer player2 = tennisGame.getPlayer2();
        player2.setScore(TennisScore.LOVE);

        mockRandomizer(tennisGame, true);

        GameWinnerException gameWinnerException = assertThrows(GameWinnerException.class, tennisGame::playRound);
        assertEquals("Player 1 Wins !", gameWinnerException.getMessage());
    }

    @Test
    void should_win_on_advantage() throws IllegalAccessException {
        TennisGame tennisGame = new TennisGame();
        TennisPlayer player1 = tennisGame.getPlayer1();
        player1.setScore(TennisScore.FORTY);
        tennisGame.advantage = tennisGame.getPlayer2();

        mockRandomizer(tennisGame, false);

        GameWinnerException gameWinnerException = assertThrows(GameWinnerException.class, tennisGame::playRound);
        assertEquals("Player 2 Wins !", gameWinnerException.getMessage());
    }

    @Test
    void should_throw_when_max_round_number_has_been_reached() {
        TennisGame tennisGame = new TennisGame();
        assertThrows(MaxRoundNumberReached.class, () -> tennisGame.playGame(1));
    }

    private static void mockRandomizer(TennisGame tennisGame, boolean player1Wins) throws IllegalAccessException {
        Field randomizer = Arrays.stream(tennisGame.getClass().getDeclaredFields())
                .filter(field -> field.getName().equals("randomizer"))
                .findFirst()
                .orElseThrow();
        randomizer.setAccessible(true);
        randomizer.set(tennisGame, new MyRandomizer(player1Wins));
        randomizer.setAccessible(false);
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
