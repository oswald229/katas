package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;

public class TennisGame {

    private static final String WINNER_STRING_FORMAT = "%s Wins !";
    private static final String DEFAULT_PLAYER_1_NAME = "Player 1";
    private static final String DEFAULT_PLAYER_2_NAME = "Player 2";
    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final Random randomizer;
    private final TennisGamePrinter gamePrinter;
    private TennisPlayer advantage;
    LinkedList<TennisPlayer> winningRounds;


    TennisGame() {
        this(new TennisPlayer(DEFAULT_PLAYER_1_NAME, new PlayerScore()), new TennisPlayer(DEFAULT_PLAYER_2_NAME, new PlayerScore()));
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.gamePrinter = new TennisGameConsolePrinter(this);
        this.randomizer = new Random();
        this.advantage = TennisPlayer.EMPTY_PLAYER;
        this.winningRounds = new LinkedList<>();
    }

    @Deprecated(forRemoval = true)
    public void setAdvantage(TennisPlayer advantage) {
        this.advantage = advantage;
    }

    @Deprecated(forRemoval = true)
    TennisPlayer advantage() {
        return advantage;
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
        winningRounds.add(player);
        return manageWinningRound();
    }

    private TennisPlayer manageWinningRound() {
        TennisPlayer roundWinner = Optional.ofNullable(winningRounds.peekLast()).orElse(TennisPlayer.EMPTY_PLAYER);
        if (wonAdvantage()) {
            this.advantage = roundWinner;
            return this.advantage;
        } else if (wonGame(roundWinner)) {
            throw new GameWinnerException(WINNER_STRING_FORMAT.formatted(roundWinner.name()));
        }
        this.advantage = TennisPlayer.EMPTY_PLAYER;
        roundWinner.increaseScore();
        return roundWinner;
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
