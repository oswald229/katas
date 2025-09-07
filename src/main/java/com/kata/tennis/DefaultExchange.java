package com.kata.tennis;

record DefaultExchange(
        TennisPlayer winner,
        TennisScore winnerScore,
        TennisScore looserScore
) implements Exchange {
    DefaultExchange(TennisPlayer winner, TennisPlayer looser) {
        this(winner, winner.getScore(), looser.getScore());
        if (wasDeuces()) {
            winner.advantage();
            return;
        }
        if (wasLostAdvantage()) {
            looser.decreaseScore();
            return;
        }
        winner.increaseScore();
    }

    @Override
    public boolean wasGameWinning() {
        if (winnerScore().equals(TennisScore.AV)) {
            return true;
        }
        return noAdvantage() && !wasDeuces() && winnerScore.equals(TennisScore.FORTY);
    }

    private boolean wasDeuces() {
        if (noAdvantage()) {
            return winnerScore.equals(TennisScore.FORTY) && looserScore.equals(TennisScore.FORTY);
        }
        return false;
    }

    private boolean noAdvantage() {
        return !winnerScore.equals(TennisScore.AV) && !looserScore.equals(TennisScore.AV);
    }

    private boolean wasLostAdvantage() {
        return looserScore.equals(TennisScore.AV);
    }

}
