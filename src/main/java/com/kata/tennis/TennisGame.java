package com.kata.tennis;

import java.util.Random;

public class TennisGame {

    private static final String WINNER_STRING_FORMAT = "%s Wins !";
    private static final String DEFAULT_PLAYER_1_NAME = "Player 1";
    private static final String DEFAULT_PLAYER_2_NAME = "Player 2";
    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final Random randomizer;
    private final TennisGamePrinter gamePrinter;
    TennisPlayer advantage;

    TennisPlayer advantage() {
        return advantage;
    }

    TennisGame() {
        this.player1 = new TennisPlayer(DEFAULT_PLAYER_1_NAME);
        this.player2 = new TennisPlayer(DEFAULT_PLAYER_2_NAME);
        this.gamePrinter = new TennisGameConsolePrinter(this);
        this.randomizer = new Random();
        this.advantage = TennisPlayer.EMPTY_PLAYER;

    }


    public void playGame(int maxRound) {
        while (maxRound > 0) {
            playRound();
            gamePrinter.print();
            maxRound--;
        }
        throw new MaxRoundNumberReached();
    }

    public TennisPlayer playRound() {
        return manageWinningRound(roundWinner());
    }

    private TennisPlayer roundWinner() {
        return randomizer.nextBoolean() ? player1 : player2;
    }

    private TennisPlayer manageWinningRound(TennisPlayer player) {
        if (wonAdvantage()) {
            advantage = player;
        } else if (wonGame(player)) {
            throw new GameWinnerException(WINNER_STRING_FORMAT.formatted(player.getName()));
        } else {
            advantage = TennisPlayer.EMPTY_PLAYER;
            player.increaseScore();
        }
        return player;
    }

    private boolean wonGame(TennisPlayer player) {
        return hasAdvantage(player) || !ongoingAdvantage() && !deuces() && player.getScore().equals(TennisScore.FORTY);
    }

    private boolean wonAdvantage() {
        return !ongoingAdvantage() && deuces();
    }

    private boolean ongoingAdvantage() {
        return !advantage.equals(TennisPlayer.EMPTY_PLAYER);
    }

    private boolean hasAdvantage(TennisPlayer player) {
        return ongoingAdvantage() && advantage.equals(player);
    }

    private boolean deuces() {
        return player1.getScore().equals(TennisScore.FORTY) && player2.getScore().equals(TennisScore.FORTY);
    }


    public TennisPlayer getPlayer1() {
        return this.player1;
    }

    public TennisPlayer getPlayer2() {
        return this.player2;
    }

    @Override
    public String toString() {
        return this.gamePrinter.print();
    }
}
