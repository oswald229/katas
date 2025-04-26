package com.kata.pokerhands;

import java.util.List;

public class ThreeOfAKindHandStrategy implements HandStrategy {
    @Override
    public boolean matches(List<Card> cards) {
        return isThreeOfAKind(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.THREE_OF_A_KIND;
    }

    private boolean isThreeOfAKind(List<Card> cards) {
        var groupedCard = new Cards(cards).groupedByCardValue();
        return groupedCard.size() == 3 && groupedCard.containsValue(3) && groupedCard.containsValue(1);
    }
}
