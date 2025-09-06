package com.kata.tennis;

import java.util.Optional;

class TennisScoreTracker {

    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final Set set;
    private TennisPlayer advantage;

    private TennisScoreTracker(TennisPlayer player1, TennisPlayer player2,
                       TennisPlayer advantage, Set set) {
        this.player1 = player1;
        this.player2 = player2;
        this.advantage = advantage;
        this.set = set;
    }

    TennisScoreTracker(TennisPlayer player1, TennisPlayer player2,
                       Set set) {
        this(player1, player2, TennisPlayer.EMPTY_PLAYER, set);
    }


    public TennisScoreTracker withAdvantage(TennisPlayer roundWinner) {
        return new TennisScoreTracker(player1, player2, roundWinner, set);
    }

    private boolean deucesOngoing() {
        if (advantage.equals(TennisPlayer.EMPTY_PLAYER)) {
            return player1.getScore().equals(TennisScore.FORTY) && player2.getScore().equals(TennisScore.FORTY);
        }
        return false;
    }

    private TennisPlayer lead() {
        return set.lead();
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
        if (deucesOngoing() && set.lastGameWinner().equals(lead())) {
            this.advantage = lead();
            return;
        }
        if (set.lastGameLoser().getScore().equals(TennisScore.AV)){
            set.lastGameLoser().decreaseScore();
            this.advantage = TennisPlayer.EMPTY_PLAYER;
            return;
        }
        lead().increaseScore();
    }

    public TennisPlayer advantage() {
        return advantage;
    }
}
