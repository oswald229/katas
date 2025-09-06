package com.kata.tennis;

import java.util.LinkedList;
import java.util.Random;

public class TennisGame {

    private static final String WINNER_STRING_FORMAT = "%s Wins !";
    private static final String DEFAULT_PLAYER_1_NAME = "Player 1";
    private static final String DEFAULT_PLAYER_2_NAME = "Player 2";
    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final Random randomizer;
    private final TennisGamePrinter gamePrinter;
    private final Set set;
    private TennisScoreTracker score;


    TennisGame() {
        this(new TennisPlayer(DEFAULT_PLAYER_1_NAME, new PlayerScore()),
                new TennisPlayer(DEFAULT_PLAYER_2_NAME, new PlayerScore()));
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2) {
        this(player1, player2,  new Random(), new Set());
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2, TennisScoreTracker score, Random randomizer) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = score;
        this.randomizer = randomizer;
        this.gamePrinter = new TennisGameConsolePrinter(player1, player2, () -> this.score.advantage());
        this.set = new Set(new LinkedList<>());
    }


    TennisGame(TennisPlayer player1, TennisPlayer player2, Random randomizer, Set set) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = new TennisScoreTracker(player1, player2, set);
        this.randomizer = randomizer;
        this.set = set;
        this.gamePrinter = new TennisGameConsolePrinter(player1, player2, () -> this.score.advantage());
    }

    @Deprecated(forRemoval = true)
    public void setAdvantage(TennisPlayer advantage) {
        this.score = score.withAdvantage(advantage);
    }

    @Deprecated(forRemoval = true)
    TennisPlayer advantage() {
        return score.advantage();
    }

    public void playGame(int maxRound) {
        while (maxRound > 0) {
            playRound();
            gamePrinter.print();
            maxRound--;
        }
        throw new MaxRoundNumberReached();
    }

    protected void playRound() {
        boolean player1Won = randomizer.nextBoolean();
        if (player1Won) {
            set.addGame(new Game(player1, player2));
        } else {
            set.addGame(new Game(player2, player1));
        }
        if (this.score.winner().isPresent()) {
            throw new GameWinnerException(WINNER_STRING_FORMAT.formatted(score.winner().orElseThrow().toString()));
        }
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
