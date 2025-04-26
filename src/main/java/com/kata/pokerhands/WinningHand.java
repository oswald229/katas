package com.kata.pokerhands;

public record WinningHand(String winner, PokerHandEnum hand) implements Winner {
    public WinningHand(String winner, PokerHand blackHand) {
        this(winner, blackHand.tellHand());
    }
}
