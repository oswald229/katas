package com.kata.tennis;

import java.io.PrintStream;

// TODO: Maybe this should be an abstract class, in order to encapsulate the game, or players.
public interface TennisGamePrinter {
    void print(PrintStream out);

    default void print() {
        print(System.out);
    }

    String output();
}
