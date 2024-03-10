package com.kata.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingFrame {
    public static final int FRAME_PINS_AMOUNT = 10;
    private final ArrayList<BowlingRoll> rolls;
    private int rollsToPlay = 2;

    private int rollsPlayed = 0;

    public BowlingFrame() {
        this.rolls = new ArrayList<>();
    }


    public int getScore() {
        return rolls.stream()
                .map(BowlingRoll::knockedPins)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public void playRoll(int knockedPins) {
        if (isCompleted()) {
            return;
        }
        this.rolls.add(new BowlingRoll(knockedPins));
        ++rollsPlayed;
    }


    public boolean isCompleted() {
        if (rollsToPlay == 3) {
            return rollsToPlay == rollsPlayed;
        }

        return isStrike() || isSpare() || rollsPlayed == rollsToPlay;
    }

    public BowlingBonus getBonus() {

        if (isStrike()) {
            return BowlingBonus.STRIKE;
        }

        if (isSpare()) {
            return BowlingBonus.SPARE;
        }

        return BowlingBonus.NONE;
    }

    private boolean isSpare() {
        return (getKnockedOnFirstRoll() + getKnockedOnSecondRoll()) == FRAME_PINS_AMOUNT;
    }

    private boolean isStrike() {
        return getKnockedOnFirstRoll() == FRAME_PINS_AMOUNT;
    }

    public int getKnockedOnFirstRoll() {
        if (this.rolls.isEmpty()) {
            return 0;
        }
        return this.rolls.get(0).knockedPins();
    }

    public int getKnockedOnSecondRoll() {
        if (this.rolls.size() > 1) {
            return this.rolls.get(1).knockedPins();
        }
        return 0;
    }

    public int rollsToPlay() {
        return rollsToPlay;
    }

    public void addExtraRoll() {
        rollsToPlay = 3;
    }

    public List<BowlingRoll> getRolls() {
        return rolls;
    }
}
