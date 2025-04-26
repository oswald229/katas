package com.kata.pokerhands;

import java.util.List;

public class FlushHandStrategy implements HandStrategy {
    @Override
    public boolean matches(List<Card> cards) {
        return isFlushHand(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.FLUSH;
    }

    static boolean isFlushHand(List<Card> cards) {
        Suit suit = cards.get(0).getSuit();
        return cards.stream().filter(card -> card.getSuit().equals(suit))
                .count() == cards.size();
    }
}
