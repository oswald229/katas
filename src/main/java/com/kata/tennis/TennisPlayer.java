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

    public TennisPlayer(TennisScore tennisScore) {
        this("Player", new PlayerScore(tennisScore));
    }

    public TennisPlayer(String name, TennisScore tennisScore) {
        this(name, new PlayerScore(tennisScore));
    }

    public TennisScore getScore() {
        return score.score();
    }

    public void increaseScore() {
        this.score = this.score.nextScore();
    }

    public String name() {
        return this.name;
    }
}
