package com.kata.tennis;

import java.util.Random;

public class TennisGame {

    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final Random randomizer;
    private final TennisGamePrinter gamePrinter;
    private Set set;


    TennisGame() {
        this(new TennisPlayer("Player 1"), new TennisPlayer("Player 2"));
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2) {
        this(player1, player2, new Random());
    }


    TennisGame(TennisPlayer player1, TennisPlayer player2, Random randomizer) {
        this.player1 = player1;
        this.player2 = player2;
        this.set = new Set(player1, player2);
        this.randomizer = randomizer;
        this.gamePrinter = new TennisGameConsolePrinter(player1, player2, () -> this.set.advantage());
    }

    public void play(int maxGames) {
        while (maxGames > 0) {
            playRound();
            gamePrinter.print();
            maxGames--;
        }
        throw new MaxRoundNumberReached();
    }

    protected void playRound() {
        playGame();
        if (this.set.winner().isPresent()) {
            new Winner(this.set.winner().get()).output();
        }
    }

    private void playGame() {
        if (randomizer.nextBoolean()) {
            set.addGame(new Game(player1, player2));
            return;
        }
        set.addGame(new Game(player2, player1));
    }

    @Override
    public String toString() {
        return this.gamePrinter.output();
    }

    @Deprecated(forRemoval = true)
    public void setAdvantage(TennisPlayer advantage) {
        this.set = set.withAdvantage(advantage);
    }

    @Deprecated(forRemoval = true)
    TennisPlayer advantage() {
        return set.advantage();
    }

    @Deprecated(forRemoval = true)
    public TennisPlayer getPlayer1() {
        return this.player1;
    }

    @Deprecated(forRemoval = true)
    public TennisPlayer getPlayer2() {
        return this.player2;
    }
}
