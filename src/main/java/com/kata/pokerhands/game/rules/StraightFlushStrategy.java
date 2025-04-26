package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHandEnum;

public class StraightFlushStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return StraightHandStrategy.isStraightHand(cards.content())
                && FlushHandStrategy.isFlushHand(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.STRAIGHT_FLUSH;
    }
}
