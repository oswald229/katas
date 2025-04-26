package com.kata.pokerhands;

import java.util.List;

interface HandStrategy {
    boolean matches(Cards cards);

    default PokerHandEnum hand() {
        return PokerHandEnum.HIGH_CARD;
    }
}
