package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.CardValue;
import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHandEnum;

import java.util.LinkedList;

public class RoyalFlushHandStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return isRoyalFlush(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.ROYAL_FLUSH;
    }

    static boolean isRoyalFlush(Cards cards) {
        return cards.content().get(0).getValue().equals(CardValue.ACE)
                && FlushHandStrategy.isFlushHand(cards)
                && StraightHandStrategy.isStraightHand(new LinkedList<>(cards.content()));
    }
}
