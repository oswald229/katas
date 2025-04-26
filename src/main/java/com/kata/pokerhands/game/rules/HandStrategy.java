package com.kata.pokerhands.game.rules;

import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHandEnum;

public interface HandStrategy {
    boolean matches(Cards cards);

    default PokerHandEnum hand() {
        return PokerHandEnum.HIGH_CARD;
    }
}
