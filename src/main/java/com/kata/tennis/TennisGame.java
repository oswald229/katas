package com.kata.tennis;

import java.util.LinkedList;

public class TennisGame {

    private final TennisPlayer player1;
    private final TennisPlayer player2;
    private final TennisGamePrinter gamePrinter;
    private final Game game;
    private final Referee referee;


    TennisGame() {
        this(new TennisPlayer("Player 1"), new TennisPlayer("Player 2"));
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2) {
        this(player1, player2, new DefaultReferee(), new Game(new LinkedList<>()));
    }

    TennisGame(TennisPlayer player1, TennisPlayer player2, Referee referee) {
        this.player1 = player1;
        this.player2 = player2;
        this.referee = referee;
        this.game = new Game(new LinkedList<>());
        this.gamePrinter = new TennisGameConsolePrinter(this.player1, this.player2);
    }

    private TennisGame(TennisPlayer player1, TennisPlayer player2, Referee referee, Game game) {
        this.player1 = player1;
        this.player2 = player2;
        this.game = game;
        this.referee = referee;
        this.gamePrinter = new TennisGameConsolePrinter(this.player1, this.player2);
    }

    public void play(int maxExchanges) {
        while (maxExchanges > 0) {
            playExchange();
            gamePrinter.print();
            maxExchanges--;
        }
        throw new MaxRoundNumberReached();
    }

    protected void playExchange() {
        Exchange playedExchange = this.referee.compute(this.player1, this.player2);
        game.add(playedExchange);
        game.winner().ifPresent(player -> new Winner(player).output());
    }


    @Override
    public String toString() {
        return this.gamePrinter.output();
    }

}
