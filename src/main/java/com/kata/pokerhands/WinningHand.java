package com.kata.pokerhands;

public record WinningHand(String winner, PokerHandEnum hand) {
    public WinningHand(String winner, PokerHand blackHand) {
        this(winner, blackHand.tellHand());
    }
}
