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
            case FORTY -> new PlayerScore(TennisScore.AV);
            default -> this;
        };
    }

    public PlayerScore decreased() {
        if (this.score.equals(TennisScore.AV)){
            return new PlayerScore(TennisScore.FORTY);
        }
        return this;
    }
}
