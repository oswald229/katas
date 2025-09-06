package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Card;
import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHandEnum;

public class FlushHandStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return isFlushHand(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.FLUSH;
    }

    static boolean isFlushHand(Cards cards) {
        return cards.content().stream().map(Card::getSuit).distinct().count() == 1;
    }
}
