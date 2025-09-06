package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Card;
import com.kata.pokerhands.game.model.CardValue;
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
        int bitmask = 0;

        for (Card card : cards) {
            bitmask |= (1 << CardValue.indexOf(card.getValue()));
        }

        // Look for 5 consecutive bits
        for (int i = 2; i <= 10; i++) {
            if ((bitmask >> i & 0b11111) == 0b11111) {
                return true;
            }
        }

        return false;
    }
}
