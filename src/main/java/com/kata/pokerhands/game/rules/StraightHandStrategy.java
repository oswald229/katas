package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Card;
import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHandEnum;

import java.util.List;

public class StraightHandStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return isStraightHand(cards.content());
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.STRAIGHT;
    }

    static boolean isStraightHand(List<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (!cards.get(i).isNeighbourOf(cards.get(i + 1))) {
                return false;
            }
        }
        return true;
    }
}
