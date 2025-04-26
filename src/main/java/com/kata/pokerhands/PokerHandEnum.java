package com.kata.pokerhands;

public enum PokerHandEnum {
    HIGH_CARD,
    PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    ROYAL_FLUSH, STRAIGHT_FLUSH;

    public boolean strongerThan(PokerHandEnum hand) {
        return new PokerHandComparator().compare(this, hand) > 0;
    }
}
