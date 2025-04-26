package com.kata.pokerhands;

public interface PokerHandReader {
    PokerHandEnum tellHandFor(PokerHand pokerHand);

    default boolean areEquals(PokerHand blackHand, PokerHand whiteHand) {
        return tellHandFor(blackHand).equals(tellHandFor(whiteHand));
    }
}
