package com.kata.pokerhands;

public interface PokerHandReader {
    boolean areEquals(PokerHand blackHand, PokerHand whiteHand);

    PokerHandEnum tellHandFor(PokerHand pokerHand);
}
