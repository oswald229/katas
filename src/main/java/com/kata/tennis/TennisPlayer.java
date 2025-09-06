package com.kata.tennis;

public class TennisPlayer {

    private TennisScore score;
    private String name;

    TennisPlayer() {
        initRequiredFields();
    }

    public TennisPlayer(String name) {
        initRequiredFields();
        this.name = name;
    }

    private void initRequiredFields() {
        score = TennisScore.LOVE;
    }


    public TennisScore getScore() {
        return score;
    }

    public void setScore(TennisScore newScore) {
        score = newScore;
    }

    public void increaseScore() {
        switch (score) {
            case LOVE -> setScore(TennisScore.FIFTEEN);
            case FIFTEEN -> setScore(TennisScore.THIRTY);
            case THIRTY -> setScore(TennisScore.FORTY);
            default -> {
            }
        }
    }

    public String getName() {
        return this.name;
    }
}
