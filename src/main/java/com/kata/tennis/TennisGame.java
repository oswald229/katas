package com.kata.tennis;

import java.util.Random;

public class TennisGame {

    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final TennisGamePrinter gamePrinter;
    private final Set set;
    private final Referee referee;


    TennisGame() {
        this(new TennisPlayer("Player 1"), new TennisPlayer("Player 2"));
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2) {
        this(player1, player2, new Set(), (p1, p2) -> {
            if (new Random().nextBoolean()) {
                return new DefaultGame(p1, p2);
            }
            return new DefaultGame(p2, p1);
        });
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2, Referee referee) {
        this.player1 = player1;
        this.player2 = player2;
        this.referee = referee;
        this.set = new Set();
        this.gamePrinter = new TennisGameConsolePrinter(this.player1, this.player2);
    }

    private TennisGame(TennisPlayer player1, TennisPlayer player2, Set set, Referee referee) {
        this.player1 = player1;
        this.player2 = player2;
        this.set = set;
        this.referee = referee;
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
        Game playedGame = this.referee.compute(this.player1, this.player2);
        set.addGame(playedGame);
        if (this.set.winner().isPresent()) {
            new Winner(this.set.winner().get()).output();
        }
    }


    @Override
    public String toString() {
        return this.gamePrinter.output();
    }

}
