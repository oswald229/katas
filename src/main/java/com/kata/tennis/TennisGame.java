package com.kata.tennis;

import lombok.Getter;

import java.util.Random;

public class TennisGame {

    private static final String WINNER_STRING_FORMAT = "%s Wins !";
    private static final String DEFAULT_PLAYER_1_NAME = "Player 1";
    private static final String DEFAULT_PLAYER_2_NAME = "Player 2";
    @Getter
    private final TennisPlayer player1;
    @Getter
    private final TennisPlayer player2;
    private final Random randomizer = new Random();
    private final TennisGamePrinter gamePrinter;
    TennisPlayer advantage;


    TennisGame() {
        this.player1 = new TennisPlayer(DEFAULT_PLAYER_1_NAME);
        this.player2 = new TennisPlayer(DEFAULT_PLAYER_2_NAME);
        this.gamePrinter = new TennisGameConsolePrinter();
    }


    public void playGame(int maxRound) {
        while (maxRound > 0) {
            playRound();
            gamePrinter.printGameState(this);
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
            advantage = null;
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
        return advantage != null;
    }

    private boolean hasAdvantage(TennisPlayer player) {
        return ongoingAdvantage() && advantage.equals(player);
    }

    private boolean deuces() {
        return player1.getScore().equals(TennisScore.FORTY) && player2.getScore().equals(TennisScore.FORTY);
    }


}
