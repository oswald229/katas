package com.kata.tennis;

import java.io.PrintStream;

public class TennisGameConsolePrinter implements TennisGamePrinter {
    private final DefaultScoreOutput scoreLine;

    public TennisGameConsolePrinter(TennisPlayer player1, TennisPlayer player2) {
        this.scoreLine = new DefaultScoreOutput(player1, player2);
    }

    @Override
    public void print(PrintStream out) {
        out.println(scoreLine);
    }

    @Override
    public String output() {
        return scoreLine.toString();
    }

}
