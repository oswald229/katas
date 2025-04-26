package com.kata.pokerhands;

import java.util.List;

class PairHandStrategy implements HandStrategy {
    @Override
    public boolean matches(List<Card> cards) {
        return isPair(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.PAIR;
    }

    private static boolean isPair(List<Card> cards) {
        var groupedCard = new Cards(cards).groupedByCardValue();
        return groupedCard.size() == 4 && groupedCard.containsValue(2);
    }
}
