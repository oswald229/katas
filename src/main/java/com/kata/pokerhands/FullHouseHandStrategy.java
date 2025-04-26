package com.kata.pokerhands;

import java.util.List;

public class FullHouseHandStrategy implements HandStrategy {
    @Override
    public boolean matches(List<Card> cards) {
        return isFullHouse(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.FULL_HOUSE;
    }

    private static boolean isFullHouse(List<Card> cards) {
        var groupedCard = new Cards(cards).groupedByCardValue();
        // A size 2 distribution means 3 and 2 distinct duplicates, hence a FullHouse.
        return groupedCard.size() == 2;
    }
}
