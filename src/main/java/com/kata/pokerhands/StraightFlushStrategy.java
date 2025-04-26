package com.kata.pokerhands;

import java.util.LinkedList;

public class StraightFlushStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return StraightHandStrategy.isStraightHand(new LinkedList<>(cards.content()))
                && FlushHandStrategy.isFlushHand(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.STRAIGHT_FLUSH;
    }
}
