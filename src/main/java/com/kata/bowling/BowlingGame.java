package com.kata.bowling;


import java.util.List;
import java.util.stream.IntStream;

import static com.kata.bowling.BowlingFrame.TOTAL_FRAME_AMOUNT;

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
                .range(0, TOTAL_FRAME_AMOUNT)
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

    private void computeScore() {
        score = 0;
        this.frames.forEach(frame -> {
            score += frame.getScore();
            if (hasBonus(frame)) {
                manageFrameBonus(frame);
            }
        });
    }

    private void setNextFrame() {
        if (currentFrame.isCompleted() && gameHasNotYetPlayedFramesLeft()) {
            currentFrame = frames.get(++currentFrameIndex);
        }
    }

    private boolean gameHasNotYetPlayedFramesLeft() {
        return currentFrameIndex < TOTAL_FRAME_AMOUNT - 1;
    }

    private static boolean hasBonus(BowlingFrame bowlingFrame) {
        return !bowlingFrame.getBonus().equals(BowlingBonus.NONE);
    }

    private void manageFrameBonus(BowlingFrame frame) {
        if (isLastFrame(frame)) {
            frame.addExtraRoll();
        } else {
            computeFrameBonus(frame);
        }
    }

    private void computeFrameBonus(BowlingFrame frame) {
        switch (frame.getBonus()) {
            case STRIKE -> handleStrikeBonus(frame);
            case SPARE -> handleSpareBonus(frame);
            case NONE -> {
                // Do nothing.
            }
        }
    }

    private boolean isLastFrame(BowlingFrame frame) {
        return frames.indexOf(frame) == TOTAL_FRAME_AMOUNT - 1;
    }

    private void handleSpareBonus(BowlingFrame frame) {
        score += frames.get(frames.indexOf(frame) + 1).getKnockedOnFirstRoll();
    }

    private void handleStrikeBonus(BowlingFrame strikeFrame) {
        BowlingFrame frameAfterStrike = getFrameAfter(strikeFrame);
        List<BowlingRoll> rollsAfterStrike = frameAfterStrike.getRolls();
        int rollsPlayedAfterStrike = rollsAfterStrike.size();

        if (rollsPlayedAfterStrike == 1) {
            handleStrikeWithTwoFramesPlayedAfter(strikeFrame);
        } else if (rollsPlayedAfterStrike >= 2) {
            handleStrikeWithOneFramePlayedAfter(strikeFrame);
        }
    }

    private BowlingFrame getFrameAfter(BowlingFrame strikeFrame) {
        int strikeFrameIndex = frames.indexOf(strikeFrame);
        return frames.get(strikeFrameIndex + 1);
    }

    private void handleStrikeWithOneFramePlayedAfter(BowlingFrame strikeFrame) {
        score += getFrameAfter(strikeFrame)
                .getRolls()
                .subList(0, 2)
                .stream()
                .map(BowlingRoll::knockedPins)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private void handleStrikeWithTwoFramesPlayedAfter(BowlingFrame strikeFrame) {
        BowlingFrame firstFrameAfterStrike = getFrameAfter(strikeFrame);
        score += firstFrameAfterStrike.getRolls().get(0).knockedPins();

        if (thereIsAFrameAfter(firstFrameAfterStrike)) {
            computeFromSecondFrameAfterStrike(getFrameAfter(firstFrameAfterStrike));
        }
    }

    private boolean thereIsAFrameAfter(BowlingFrame firstFrameAfterStrike) {
        return frames.indexOf(firstFrameAfterStrike) + 1 < TOTAL_FRAME_AMOUNT;
    }

    private void computeFromSecondFrameAfterStrike(BowlingFrame frame) {
        List<BowlingRoll> frameRolls = frame.getRolls();
        score += hasKnockedPins(frameRolls) ?
                frameRolls.get(0).knockedPins() : 0;
    }

    private static boolean hasKnockedPins(List<BowlingRoll> rolls) {
        return !rolls.isEmpty();
    }
}
