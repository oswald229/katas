package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

class TennisScoreTracker {

    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final LinkedList<TennisPlayer> roundsWinners;
    private TennisPlayer advantage;

    TennisScoreTracker(TennisPlayer player1, TennisPlayer player2, LinkedList<TennisPlayer> roundsWinners,
                       TennisPlayer advantage){
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

    boolean deuces() {
        return player1.getScore().equals(TennisScore.FORTY) && player2.getScore().equals(TennisScore.FORTY);
    }

    void addLastRoundWinner(TennisPlayer player) {
        roundsWinners.add(player);
    }

    TennisPlayer lastWinner() {
        return roundsWinners.peekLast();
    }

    public boolean ongoingAdvantage() {
        return !advantage.equals(TennisPlayer.EMPTY_PLAYER);
    }

    public boolean hasAdvantage(TennisPlayer player) {
        return ongoingAdvantage() && advantage.equals(player);
    }

    public TennisScoreTracker withAdvantage(TennisPlayer roundWinner) {
        return new TennisScoreTracker(player1, player2, roundsWinners, roundWinner);
    }


    public boolean gameWon() {
        TennisPlayer player = roundsWinners.peekLast();
        return this.hasAdvantage(player) || !this.ongoingAdvantage() && !this.deuces() && player.getScore().equals(TennisScore.FORTY);
    }

    public Optional<TennisPlayer> winner() {
        return Optional.ofNullable(roundsWinners.peekLast());
    }


    boolean wonAdvantage() {
        if (ongoingAdvantage()) return false;
        return deuces();
    }

    public TennisPlayer advantage() {
        return advantage;
    }

    void manageWinningRound() {
        TennisPlayer roundWinner = lastWinner();
        if (wonAdvantage()) {
            this.advantage = roundWinner;
            return;
        }
        this.advantage = TennisPlayer.EMPTY_PLAYER;
        roundWinner.increaseScore();
    }
}
