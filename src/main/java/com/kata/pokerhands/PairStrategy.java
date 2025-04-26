package com.kata.pokerhands;

import java.util.List;

class PairStrategy implements HandStrategy {
    @Override
    public boolean matches(List<Card> cards) {
        return PokerHandReader.isPair(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.PAIR;
    }
}
