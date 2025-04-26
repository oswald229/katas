package com.kata.pokerhands;

interface HandStrategy {
    boolean matches(Cards cards);

    default PokerHandEnum hand() {
        return PokerHandEnum.HIGH_CARD;
    }
}
