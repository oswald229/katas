package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Suit;
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
        Suit suit = cards.content().get(0).getSuit();
        return cards.content().stream().filter(card -> card.getSuit().equals(suit))
                .count() == cards.content().size();
    }
}
