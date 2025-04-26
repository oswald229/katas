package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHandEnum;

public class TwoPairHandStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return isTwoPair(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.TWO_PAIR;
    }

    private static boolean isTwoPair(Cards cards) {
        var groupedCard = cards.groupedByCardValue();
        return groupedCard.size() == 3 && groupedCard.containsValue(1);
    }
}
