package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

class TennisScoreTracker {

    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final LinkedList<TennisPlayer> roundsWinners;
    private final Rounds rounds;
    private TennisPlayer advantage;

    TennisScoreTracker(TennisPlayer player1, TennisPlayer player2, LinkedList<TennisPlayer> roundsWinners,
                       TennisPlayer advantage, Rounds rounds) {
        this.player1 = player1;
        this.player2 = player2;
        this.roundsWinners = roundsWinners;
        this.advantage = advantage;
        this.rounds = rounds;
    }

    TennisScoreTracker(TennisPlayer player1, TennisPlayer player2, LinkedList<TennisPlayer> roundsWinners, Rounds rounds) {
        this(player1, player2, roundsWinners, TennisPlayer.EMPTY_PLAYER, rounds);
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

    private boolean hasAdvantage(TennisPlayer player) {
        return !advantage.equals(TennisPlayer.EMPTY_PLAYER) && advantage.equals(player);
    }

    public TennisScoreTracker withAdvantage(TennisPlayer roundWinner) {
        return new TennisScoreTracker(player1, player2, roundsWinners, roundWinner, rounds);
    }


    public Optional<TennisPlayer> winner() {
        return Optional.ofNullable(lead())
                .filter(this::wonGame);
    }

    public boolean wonGame(TennisPlayer player) {
        return this.hasAdvantage(player)
                || advantage.equals(TennisPlayer.EMPTY_PLAYER)
                && !this.deucesOngoing() && player.getScore().equals(TennisScore.FORTY);
    }


    void update() {
        if (deucesOngoing()) {
            this.advantage = lead();
            return;
        }
        this.advantage = TennisPlayer.EMPTY_PLAYER;
        lead().increaseScore();
    }

    public TennisPlayer advantage() {
        return advantage;
    }
}
