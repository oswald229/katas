package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHandEnum;

public class PairHandStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return isPair(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.PAIR;
    }

    private static boolean isPair(Cards cards) {
        var groupedCard = cards.groupedByCardValue();
        return groupedCard.size() == 4 && groupedCard.containsValue(2);
    }
}
