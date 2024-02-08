package com.kata.tennis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TennisPlayerTest {
    @Test
    void should_have_a_score() {

        TennisPlayer tennisPlayer = new TennisPlayer();

        assertEquals(TennisScore.LOVE, tennisPlayer.getScore());
    }

    @Test
    void should_update_score() {
        TennisPlayer tennisPlayer = new TennisPlayer();
        TennisScore tennisScore = TennisScore.LOVE;
        tennisPlayer.setScore(tennisScore);

        assertEquals(tennisScore, tennisPlayer.getScore());
    }

    @Test
    void should_increase_score_according_to_the_current_one() {
        TennisPlayer tennisPlayer = new TennisPlayer();

        assertEquals(TennisScore.LOVE, tennisPlayer.getScore());
        tennisPlayer.increaseScore();
        assertEquals(TennisScore.FIFTEEN, tennisPlayer.getScore());
        tennisPlayer.increaseScore();
        assertEquals(TennisScore.THIRTY, tennisPlayer.getScore());
        tennisPlayer.increaseScore();
        assertEquals(TennisScore.FORTY, tennisPlayer.getScore());
    }

    @Test
    void can_have_a_name() {
        String name = "John Doe";
        TennisPlayer player = new TennisPlayer(name);
        assertEquals(name, player.getName());
    }
}
