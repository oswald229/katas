package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHandEnum;

public class ThreeOfAKindHandStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return isThreeOfAKind(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.THREE_OF_A_KIND;
    }

    private boolean isThreeOfAKind(Cards cards) {
        var groupedCard = cards.groupedByCardValue();
        return groupedCard.size() == 3 && groupedCard.containsValue(3) && groupedCard.containsValue(1);
    }
}
