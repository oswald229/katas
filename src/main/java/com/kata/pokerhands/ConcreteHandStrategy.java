package com.kata.pokerhands;

import java.util.List;
import java.util.function.Function;

record ConcreteHandStrategy(Function<List<Card>, Boolean> key, PokerHandEnum hand) implements HandStrategy {
    @Override
    public boolean accepts(List<Card> cards) {
        return key().apply(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return hand;
    }
}
