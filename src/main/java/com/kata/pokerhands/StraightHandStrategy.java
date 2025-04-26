package com.kata.pokerhands;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class StraightHandStrategy implements HandStrategy {
    static boolean isStraightHand(LinkedList<Card> cards) {
        if (cards.size() == 2) {
            return cards.get(0).isNeighbourOf(cards.get(1));
        }
        var headCard = cards.pop();
        var nextCard = cards.peek();

        if (Objects.nonNull(headCard) && Objects.nonNull(nextCard) && headCard.isNeighbourOf(nextCard)) {
            return isStraightHand(cards);
        }
        return false;
    }

    @Override
    public boolean matches(List<Card> cards) {
        return isStraightHand(new LinkedList<>(cards));
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.STRAIGHT;
    }
}
