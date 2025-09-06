package com.kata.tennis;

public record PlayerScore(TennisScore score) {
    public PlayerScore() {
        this(TennisScore.LOVE);
    }

    public PlayerScore nextScore() {
        return switch (score) {
            case LOVE -> new PlayerScore(TennisScore.FIFTEEN);
            case FIFTEEN -> new PlayerScore(TennisScore.THIRTY);
            case THIRTY -> new PlayerScore(TennisScore.FORTY);
            default -> this;
        };
    }
}
