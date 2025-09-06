package com.kata.tennis;

record DefaultScoreOutput(TennisPlayer player1, TennisPlayer player2
) {

    @Override
    public String toString() {

        if (player1.getScore().equals(TennisScore.AV)) {
            return ("(*) " + "%s  %s - %s  %s").formatted(
                    player1, player1.getScore(),
                    player2.getScore(), player2);
        }
        if (player2.getScore().equals(TennisScore.AV)) {
            return ("%s  %s - %s  %s" + " (*)")
                    .formatted(player1, player1.getScore(),
                            player2.getScore(), player2);
        }
        return "%s  %s - %s  %s".formatted(player1, player1.getScore(), player2.getScore(), player2);

    }
}
