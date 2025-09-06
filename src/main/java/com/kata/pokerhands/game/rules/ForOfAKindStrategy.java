package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHandEnum;

public class ForOfAKindStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return isForOfAKind(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.FOUR_OF_A_KIND;
    }

    private static boolean isForOfAKind(Cards cards) {
        var groupedCard = cards.groupedByCardValue();
        return groupedCard.size() == 2 && groupedCard.containsValue(4);
    }
}
