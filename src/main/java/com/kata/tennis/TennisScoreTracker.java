package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

class TennisScoreTracker {

    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final LinkedList<TennisPlayer> roundsWinners;
    private TennisPlayer advantage;

    TennisScoreTracker(TennisPlayer player1, TennisPlayer player2, LinkedList<TennisPlayer> roundsWinners,
                       TennisPlayer advantage) {
        this.player1 = player1;
        this.player2 = player2;
        this.roundsWinners = roundsWinners;
        this.advantage = advantage;
    }

    TennisScoreTracker(TennisPlayer player1, TennisPlayer player2, LinkedList<TennisPlayer> roundsWinners) {
        this(player1, player2, roundsWinners, TennisPlayer.EMPTY_PLAYER);
    }

    public TennisScoreTracker(TennisPlayer player1, TennisPlayer player2) {
        this(player1, player2, new LinkedList<>());
    }

    private boolean deucesOngoing() {
        if (advantage().equals(TennisPlayer.EMPTY_PLAYER)) {
            return player1.getScore().equals(TennisScore.FORTY) && player2.getScore().equals(TennisScore.FORTY);
        }
        return false;
    }

    TennisPlayer lastWinner() {
        return roundsWinners.peekLast();
    }

    private boolean hasAdvantage(TennisPlayer player) {
        return !advantage().equals(TennisPlayer.EMPTY_PLAYER) && advantage().equals(player);
    }

    public TennisScoreTracker withAdvantage(TennisPlayer roundWinner) {
        return new TennisScoreTracker(player1, player2, roundsWinners, roundWinner);
    }


    public boolean gameWon() {
        TennisPlayer player = roundsWinners.peekLast();
        return this.hasAdvantage(player)
                || advantage().equals(TennisPlayer.EMPTY_PLAYER) && !this.deucesOngoing() && player.getScore().equals(TennisScore.FORTY);
    }

    public Optional<TennisPlayer> winner() {
        if (gameWon()) {
            return Optional.ofNullable(roundsWinners.peekLast());
        }
        return Optional.empty();
    }


    public TennisPlayer advantage() {
        return advantage;
    }

    void addLastRoundWinner(TennisPlayer player) {
        roundsWinners.add(player);
    }

    void manageWinningRound() {
        computeAdvantage();
        if (lastWinner().equals(advantage())) {
            return;
        }
        lastWinner().increaseScore();
    }

    private void computeAdvantage() {
        if (deucesOngoing()) {
            this.advantage = lastWinner();
            return;
        }
        this.advantage = TennisPlayer.EMPTY_PLAYER;
    }
}
