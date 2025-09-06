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
    private TennisScoreTracker scoreTracker;


    TennisGame() {
        this(new TennisPlayer(DEFAULT_PLAYER_1_NAME, new PlayerScore()), new TennisPlayer(DEFAULT_PLAYER_2_NAME, new PlayerScore()));
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2) {
        this(player1, player2, new TennisScoreTracker(player1, player2), new Random());
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2, TennisScoreTracker scoreTracker, Random randomizer) {
        this.player1 = player1;
        this.player2 = player2;
        this.scoreTracker = scoreTracker;
        this.randomizer = randomizer;
        this.gamePrinter = new TennisGameConsolePrinter(player1, player2, () -> this.scoreTracker.advantage());
    }

    @Deprecated(forRemoval = true)
    public void setAdvantage(TennisPlayer advantage) {
        this.scoreTracker = scoreTracker.withAdvantage(advantage);
    }

    @Deprecated(forRemoval = true)
    TennisPlayer advantage() {
        return scoreTracker.advantage();
    }

    public void playGame(int maxRound) {
        while (maxRound > 0) {
            playRound();
            gamePrinter.print();
            maxRound--;
        }
        throw new MaxRoundNumberReached();
    }

    protected TennisPlayer playRound() {
        TennisPlayer player = randomizer.nextBoolean() ? player1 : player2;
        this.scoreTracker.addLastRoundWinner(player);
        if (this.scoreTracker.winner().isPresent()) {
            throw new GameWinnerException(WINNER_STRING_FORMAT.formatted(scoreTracker.winner().orElseThrow().name()));
        }
        scoreTracker.manageWinningRound();
        return scoreTracker.lastWinner();
    }


    @Deprecated(forRemoval = true)
    public TennisPlayer getPlayer1() {
        return this.player1;
    }

    @Deprecated(forRemoval = true)
    public TennisPlayer getPlayer2() {
        return this.player2;
    }

    @Override
    public String toString() {
        return this.gamePrinter.print();
    }
}
