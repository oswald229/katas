package com.kata.pokerhands;

import java.util.List;

public record PokerHand(List<Card> cards) {
    PokerHandEnum tellHand() {
        return PokerHandReader.tellHandFor(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PokerHand hand) {
            return tellHand().equals(hand.tellHand());
        }
        return false;
    }
}
