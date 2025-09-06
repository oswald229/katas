package com.kata.tennis;

import java.util.Optional;

class TennisScoreTracker {

    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final Rounds rounds;
    private TennisPlayer advantage;

    private TennisScoreTracker(TennisPlayer player1, TennisPlayer player2,
                       TennisPlayer advantage, Rounds rounds) {
        this.player1 = player1;
        this.player2 = player2;
        this.advantage = advantage;
        this.rounds = rounds;
    }

    TennisScoreTracker(TennisPlayer player1, TennisPlayer player2,
                       Rounds rounds) {
        this(player1, player2, TennisPlayer.EMPTY_PLAYER, rounds);
    }


    public TennisScoreTracker withAdvantage(TennisPlayer roundWinner) {
        return new TennisScoreTracker(player1, player2, roundWinner, rounds);
    }

    private boolean deucesOngoing() {
        if (advantage.equals(TennisPlayer.EMPTY_PLAYER)) {
            return player1.getScore().equals(TennisScore.FORTY) && player2.getScore().equals(TennisScore.FORTY);
        }
        return false;
    }

    private TennisPlayer lead() {
        return rounds.lead();
    }

    private boolean hadAdvantage(TennisPlayer player) {
        return advantage.equals(player);
    }


    public Optional<TennisPlayer> winner() {
        return Optional.ofNullable(lead())
                .filter(this::wonGame);
    }

    private boolean wonGame(TennisPlayer player) {
        return this.hadAdvantage(player)
                || advantage.equals(TennisPlayer.EMPTY_PLAYER)
                && !this.deucesOngoing() && player.getScore().equals(TennisScore.FORTY);
    }


    void update() {
        if (deucesOngoing() && rounds.lastGameWinner().equals(lead())) {
            this.advantage = lead();
            return;
        }
        if (rounds.lastGameLoser().getScore().equals(TennisScore.AV)){
            rounds.lastGameLoser().decreaseScore();
            this.advantage = TennisPlayer.EMPTY_PLAYER;
            return;
        }
        lead().increaseScore();
    }

    public TennisPlayer advantage() {
        return advantage;
    }
}
