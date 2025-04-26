package com.kata.pokerhands;

import java.util.List;

interface HandStrategy {
    boolean accepts(List<Card> cards);

    default PokerHandEnum hand() {
        return PokerHandEnum.HIGH_CARD;
    }
}
