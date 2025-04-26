package com.kata.pokerhands;

import java.util.LinkedList;
import java.util.List;

public class RoyalFlushHandStrategy implements HandStrategy {
    @Override
    public boolean accepts(List<Card> cards) {
        return isRoyalFlush(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.ROYAL_FLUSH;
    }

    static boolean isRoyalFlush(List<Card> cards) {
        return cards.get(0).getValue().equals(CardValue.ACE)
                && PokerHandReader.isFlushHand(cards)
                && PokerHandReader.isStraightHand(new LinkedList<>(cards));
    }
}
