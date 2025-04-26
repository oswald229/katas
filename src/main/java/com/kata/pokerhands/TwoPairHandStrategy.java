package com.kata.pokerhands;

import java.util.List;

public class TwoPairHandStrategy implements HandStrategy {
    @Override
    public boolean matches(List<Card> cards) {
        return isTwoPair(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.TWO_PAIR;
    }

    private static boolean isTwoPair(List<Card> cards) {
        var groupedCard = PokerHandReader.groupByCard(cards);
        return groupedCard.size() == 3 && groupedCard.containsValue(1);
    }
}
