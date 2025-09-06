package com.kata.tennis;

import java.util.Optional;

class Set {

    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final OldSet oldSet;
    private TennisPlayer advantage;

    Set(TennisPlayer player1, TennisPlayer player2) {
        this(player1, player2, TennisPlayer.EMPTY_PLAYER, new OldSet());
    }

    public Set withAdvantage(TennisPlayer roundWinner) {
        return new Set(player1, player2, roundWinner, oldSet);
    }

    private Set(TennisPlayer player1, TennisPlayer player2,
                TennisPlayer advantage, OldSet oldSet) {
        this.player1 = player1;
        this.player2 = player2;
        this.advantage = advantage;
        this.oldSet = oldSet;
    }

    private boolean deucesOngoing() {
        if (advantage.equals(TennisPlayer.EMPTY_PLAYER)) {
            return player1.getScore().equals(TennisScore.FORTY) && player2.getScore().equals(TennisScore.FORTY);
        }
        return false;
    }

    private TennisPlayer lead() {
        return oldSet.lead();
    }

    private boolean hadAdvantage(TennisPlayer player) {
        return advantage.equals(player);
    }


    public Optional<TennisPlayer> winner() {
        Optional<TennisPlayer> winner = Optional.ofNullable(lead())
                .filter(this::wonGame);
        if (winner.isEmpty()) {
            update();
        }
        return winner;
    }

    private boolean wonGame(TennisPlayer player) {
        return this.hadAdvantage(player)
                || advantage.equals(TennisPlayer.EMPTY_PLAYER)
                && !this.deucesOngoing() && player.getScore().equals(TennisScore.FORTY);
    }


     private void update() {
        if (deucesOngoing() && oldSet.lastGameWinner().equals(lead())) {
            this.advantage = lead();
            lead().increaseScore();
            return;
        }
        if (oldSet.lastGameLoser().getScore().equals(TennisScore.AV)) {
            oldSet.lastGameLoser().decreaseScore();
            this.advantage = TennisPlayer.EMPTY_PLAYER;
            return;
        }
        lead().increaseScore();
    }

    public TennisPlayer advantage() {
        return advantage;
    }

    public void addGame(Game game) {
       this.oldSet.addGame(game);
    }
}
