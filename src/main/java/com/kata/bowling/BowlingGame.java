package com.kata.bowling;


import java.util.List;
import java.util.stream.IntStream;

import static com.kata.bowling.BowlingFrame.FRAME_PINS_AMOUNT;

public class BowlingGame {

    private final List<BowlingFrame> frames;
    private int currentFrameIndex;

    private BowlingFrame currentFrame;
    private int score;

    BowlingGame() {
        this.score = 0;
        this.frames = initFrames();
        currentFrameIndex = 0;
        currentFrame = frames.get(currentFrameIndex);
    }

    private static List<BowlingFrame> initFrames() {
        return IntStream
                .range(0, FRAME_PINS_AMOUNT)
                .mapToObj(value -> new BowlingFrame())
                .toList();
    }


    public List<BowlingFrame> getFrames() {
        return frames;
    }

    public int score() {
        return score;
    }

    public void roll(int knockedPins) {
        currentFrame.playRoll(knockedPins);
        computeScore();
        setNextFrame();
    }

    private void setNextFrame() {
        if (currentFrame.isCompleted() && currentFrameIndex < FRAME_PINS_AMOUNT - 1) {
            currentFrame = frames.get(++currentFrameIndex);
        }
    }

    private void computeScore() {
        score = 0;

        for (int i = 0; i < this.frames.size(); i++) {

            BowlingFrame bowlingFrame = frames.get(i);

            score += bowlingFrame.getScore();

            BowlingBonus frameBonus = bowlingFrame.getBonus();

            if (i == FRAME_PINS_AMOUNT - 1 && !frameBonus.equals(BowlingBonus.NONE)) {
                bowlingFrame.addExtraRoll();
                continue;
            }

            if (frameBonus.equals(BowlingBonus.SPARE)) {
                handleSpareBonus(i);
            } else if (frameBonus.equals(BowlingBonus.STRIKE)) handleStrikeBonus(i);
        }
    }

    private void handleSpareBonus(int i) {
        score += frames.get(i + 1).getKnockedOnFirstRoll();
    }

    private void handleStrikeBonus(int i) {
        BowlingFrame nextFrame = frames.get(i + 1);

        List<BowlingRoll> nextFrameRolls = nextFrame.getRolls();

        if (nextFrameRolls.size() == 1) {
            score += nextFrameRolls.get(0).knockedPins();
            int furtherFrameIndex = i + 2;
            if (furtherFrameIndex < FRAME_PINS_AMOUNT) {
                List<BowlingRoll> furtherFrameRolls = frames.get(furtherFrameIndex).getRolls();
                if (!furtherFrameRolls.isEmpty()) {
                    score += furtherFrameRolls.get(0).knockedPins();
                }
            }
        }

        if (nextFrameRolls.size() >= 2) {
            score += nextFrameRolls.subList(0, 2)
                    .stream()
                    .map(BowlingRoll::knockedPins)
                    .reduce(Integer::sum)
                    .orElse(0);
        }
    }
}
