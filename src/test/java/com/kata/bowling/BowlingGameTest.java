package com.kata.bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingGameTest {

    @Test
    void should_have_ten_frames() {
        BowlingGame bowlingGame = new BowlingGame();

        int frameListSize = bowlingGame.getFrames().size();

        int expectedFrameAmount = 10;

        assertEquals(expectedFrameAmount, frameListSize);
    }

    @Test
    void should_init_score() {
        BowlingGame bowlingGame = new BowlingGame();

        assertEquals(0, bowlingGame.score());
    }

    @Test
    void should_update_score_when_rolling() {
        BowlingGame bowlingGame = new BowlingGame();

        bowlingGame.roll(5);

        assertEquals(5, bowlingGame.score());
    }

    @Test
    void should_handle_spare_bonus() {
        //A spare is when the player knocks down all 10 pins in two rolls.
        // The bonus for that frame is the number of pins knocked down by the next roll.
        BowlingGame bowlingGame = new BowlingGame();

        bowlingGame.roll(5);
        bowlingGame.roll(5);
        bowlingGame.roll(2);

        assertEquals(14, bowlingGame.score());

    }

    @Test
    void should_handle_spare_bonus_on_next_roll_only() {
        //A spare is when the player knocks down all 10 pins in two rolls.
        // The bonus for that frame is the number of pins knocked down by the next roll.
        BowlingGame bowlingGame = new BowlingGame();

        bowlingGame.roll(5);
        bowlingGame.roll(5);
        bowlingGame.roll(4);
        bowlingGame.roll(2);

        assertEquals(20, bowlingGame.score());

    }

    @Test
    void should_handle_strike_bonus() {
        //A strike is when the player knocks down all 10 pins on his first roll.
        // The frame is then completed with a single roll.
        // The bonus for that frame is the value of the next two rolls.
        BowlingGame bowlingGame = new BowlingGame();

        bowlingGame.roll(10);
        bowlingGame.roll(2);
        bowlingGame.roll(2);

        assertEquals(18, bowlingGame.score());

    }

    @Test
    void should_handle_strike_bonus_bis() {
        //A strike is when the player knocks down all 10 pins on his first roll.
        // The frame is then completed with a single roll.
        // The bonus for that frame is the value of the next two rolls.
        BowlingGame bowlingGame = new BowlingGame();

        bowlingGame.roll(0);
        bowlingGame.roll(10);
        bowlingGame.roll(3);
        bowlingGame.roll(5);

        assertEquals(21, bowlingGame.score());

    }

    @Test
    void should_handle_multiple_strikes() {
        BowlingGame bowlingGame = new BowlingGame();

        bowlingGame.roll(10); // 10 + ( 10 + 10 ) => 30
        bowlingGame.roll(10); // 10 + 30 + (10 + 0) => 50
        bowlingGame.roll(10); // 10 + 50 + (0 + 0) => 60

        assertEquals(60, bowlingGame.score());
    }

    @Test
    void should_handle_tenth_frame_bonus() {
        /*"""
            In the tenth frame a player who rolls a spare or strike
            is allowed to roll the extra balls to complete the frame.
            However, no more than three balls can be rolled in tenth frame.
            """*/
        BowlingGame bowlingGame = new BowlingGame();
        for (int i = 1; i <= 9; i++) {
            bowlingGame.roll(10);
        }

        // Play tenth frame as a Spare
        bowlingGame.roll(5);
        bowlingGame.roll(5);

        // Play extra ball
        bowlingGame.roll(8);

        assertEquals(273, bowlingGame.score());

    }

    @Test
    void should_handle_tenth_frame_strike_bonus() {
        /*"""
            In the tenth frame a player who rolls a spare or strike
            is allowed to roll the extra balls to complete the frame.
            However, no more than three balls can be rolled in tenth frame.
            """*/
        BowlingGame bowlingGame = new BowlingGame();
        for (int i = 1; i <= 9; i++) {
            bowlingGame.roll(10);
        }

        // Play tenth frame as a Spare
        bowlingGame.roll(10);

        // Play extra balls
        bowlingGame.roll(8);
        bowlingGame.roll(2);

        assertEquals(288, bowlingGame.score());

    }

    @Test
    void should_get_full_strike_score() {
        BowlingGame bowlingGame = new BowlingGame();
        for (int i = 0; i < 12; i++) {
            bowlingGame.roll(10);
        }

        assertEquals(300, bowlingGame.score());
    }
}
