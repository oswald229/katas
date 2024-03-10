package com.kata.bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingFrameTest {

    @Test
    void should_have_a_score_according_to_knocked_pins() {
        BowlingFrame bowlingFrame = new BowlingFrame();

        int knockedPins = 3;

        bowlingFrame.playRoll(knockedPins);

        assertEquals(knockedPins, bowlingFrame.getScore());

    }

    @Test
    void should_tell_if_frame_is_completed() {
        BowlingFrame bowlingFrame = new BowlingFrame();
        int knockedPins = 10;

        bowlingFrame.playRoll(knockedPins);

        assertTrue(bowlingFrame.isCompleted());
    }

    @Test
    void should_tell_if_frame_is_completed_on_two_roll() {
        BowlingFrame bowlingFrame = new BowlingFrame();

        bowlingFrame.playRoll(5);
        bowlingFrame.playRoll(5);

        assertTrue(bowlingFrame.isCompleted());
    }

    @Test
    void should_tell_if_frame_is_not_completed() {
        BowlingFrame bowlingFrame = new BowlingFrame();

        bowlingFrame.playRoll(5);

        assertFalse(bowlingFrame.isCompleted());
    }

    @Test
    void should_tell_if_frame_is_completed_but_not_fully_knocked() {
        BowlingFrame bowlingFrame = new BowlingFrame();

        bowlingFrame.playRoll(5);
        bowlingFrame.playRoll(2);

        assertTrue(bowlingFrame.isCompleted());
    }

    @Test
    void should_tell_when_frame_has_spare_bonus() {
        BowlingFrame bowlingFrame = new BowlingFrame();

        bowlingFrame.playRoll(5);
        bowlingFrame.playRoll(5);

        assertEquals(BowlingBonus.SPARE, bowlingFrame.getBonus());
    }

    @Test
    void should_tell_when_frame_has_strike_bonus() {
        BowlingFrame bowlingFrame = new BowlingFrame();

        bowlingFrame.playRoll(10);

        assertEquals(BowlingBonus.STRIKE, bowlingFrame.getBonus());
    }

    @Test
    void should_tell_when_frame_has_no_bonus() {
        BowlingFrame bowlingFrame = new BowlingFrame();

        bowlingFrame.playRoll(5);
        bowlingFrame.playRoll(2);

        assertEquals(BowlingBonus.NONE, bowlingFrame.getBonus());
    }

    @Test
    void should_complete_when_no_more_roll_to_play() {
        BowlingFrame bowlingFrame = new BowlingFrame();
        bowlingFrame.playRoll(10);
        bowlingFrame.playRoll(10);

        assertTrue(bowlingFrame.isCompleted());
        assertEquals(0, bowlingFrame.getKnockedOnSecondRoll());

    }

    @Test
    void should_have_two_roll_by_default() {
        BowlingFrame bowlingFrame = new BowlingFrame();

        assertEquals(2, bowlingFrame.rollsToPlay());
    }

    @Test
    void should_add_extra_roll() {
        BowlingFrame bowlingFrame = new BowlingFrame();

        bowlingFrame.addExtraRoll();

        assertEquals(3, bowlingFrame.rollsToPlay());
    }

    @Test
    void should_complete_according_to_rolls_to_play() {
        BowlingFrame bowlingFrame = new BowlingFrame();
        bowlingFrame.playRoll(5);
        bowlingFrame.playRoll(5);
        bowlingFrame.addExtraRoll();

        assertFalse(bowlingFrame.isCompleted());

    }
}
