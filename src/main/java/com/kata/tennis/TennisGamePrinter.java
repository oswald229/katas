package com.kata.tennis;

import java.io.PrintStream;
// TODO: Maybe this should be an abstract class, in order to encapsulate the game, or players.
public interface TennisGamePrinter {
    String print(PrintStream out);
    
    default String print() {
        return print(System.out);
    }
}
