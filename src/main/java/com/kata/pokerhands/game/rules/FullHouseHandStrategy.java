package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHandEnum;

public class FullHouseHandStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return isFullHouse(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.FULL_HOUSE;
    }

    private static boolean isFullHouse(Cards cards) {
        var groupedCard = cards.groupedByCardValue();
        return groupedCard.size() == 2;
    }
}
