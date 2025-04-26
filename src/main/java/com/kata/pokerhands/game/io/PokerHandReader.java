package com.kata.pokerhands.game.io;

import com.kata.pokerhands.game.model.PokerHand;
import com.kata.pokerhands.game.model.PokerHandEnum;

public interface PokerHandReader {
    PokerHandEnum tellHandFor(PokerHand pokerHand);

    default boolean areEquals(PokerHand blackHand, PokerHand whiteHand) {
        return tellHandFor(blackHand).equals(tellHandFor(whiteHand));
    }
}
