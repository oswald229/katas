package com.kata.pokerhands;

import java.util.List;

interface HandStrategy {
    boolean matches(List<Card> cards);

    default PokerHandEnum hand() {
        return PokerHandEnum.HIGH_CARD;
    }
}
