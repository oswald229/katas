package com.kata.tennis;

public class TennisPlayer {
    public static TennisPlayer EMPTY_PLAYER = new TennisPlayer();
    private final String name;
    private PlayerScore score;

    TennisPlayer() {
        this("Player", new PlayerScore());
    }

    public TennisPlayer(String name, PlayerScore score) {
        this.name = name;
        this.score = score;
    }

    public TennisScore getScore() {
        return score.score();
    }

    @Deprecated(forRemoval = true)
    public void setScore(TennisScore newScore) {
        score = new PlayerScore(newScore);
    }

    public void increaseScore() {
        this.score = this.score.nextScore();
    }

    public String name() {
        return this.name;
    }
}
