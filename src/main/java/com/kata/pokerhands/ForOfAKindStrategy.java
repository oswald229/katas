package com.kata.pokerhands;

import java.util.List;

public class ForOfAKindStrategy implements HandStrategy {
    @Override
    public boolean matches(List<Card> cards) {
        return isForOfAKind(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.FOUR_OF_A_KIND;
    }

    private static boolean isForOfAKind(List<Card> cards) {
        var groupedCard = PokerHandReader.groupByCard(cards);
        return groupedCard.size() == 2 && groupedCard.containsValue(4);
    }
}
