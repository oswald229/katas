package com.kata.tennis;

import java.io.PrintStream;
import java.util.function.Supplier;

public class TennisGameConsolePrinter implements TennisGamePrinter {
    private final DefaultScoreOutput scoreLine;

    public TennisGameConsolePrinter(TennisGame tennisGame) {
        this(tennisGame.getPlayer1(), tennisGame.getPlayer2(), tennisGame::advantage);
    }
    public TennisGameConsolePrinter(TennisPlayer player1, TennisPlayer player2, Supplier<TennisPlayer> advantage) {
        this.scoreLine = new DefaultScoreOutput(player1, player2, advantage);
    }

    @Override
    public void print(PrintStream out) {
        out.println(scoreLine.toString());
    }

    @Override
    public String output() {
        return scoreLine.toString();
    }

}
