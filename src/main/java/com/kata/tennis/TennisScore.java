package com.kata.tennis;

public enum TennisScore {
    LOVE(0),
    FIFTEEN(15),
    THIRTY(30),
    FORTY(40), AV(40);

    private final int score;

    TennisScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }
}
