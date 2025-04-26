package com.kata.pokerhands;

import java.util.LinkedList;
import java.util.List;

public class StraightFlushStrategy implements HandStrategy {
    @Override
    public boolean matches(List<Card> cards) {
        return StraightHandStrategy.isStraightHand(new LinkedList<>(cards))
                && FlushHandStrategy.isFlushHand(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.STRAIGHT_FLUSH;
    }
}
