package com.kata.tennis;

import java.util.Random;

public class TennisGame {

    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final Random randomizer;
    private final TennisGamePrinter gamePrinter;
    private final Set set;


    TennisGame() {
        this(new TennisPlayer("Player 1"), new TennisPlayer("Player 2"));
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2) {
        this(player1, player2, new Random(), new Set(player1, player2));
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2, Random randomizer) {
        this.player1 = player1;
        this.player2 = player2;
        this.randomizer = randomizer;
        this.set = new Set(this.player1, this.player2);
        this.gamePrinter = new TennisGameConsolePrinter(this.player1, this.player2);
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2, Random randomizer, Set set) {
        this.player1 = player1;
        this.player2 = player2;
        this.randomizer = randomizer;
        this.set = set;
        this.gamePrinter = new TennisGameConsolePrinter(this.player1, this.player2);
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

}
