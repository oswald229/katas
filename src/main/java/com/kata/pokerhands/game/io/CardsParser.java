package com.kata.pokerhands.game.io;

import com.kata.pokerhands.game.model.PokerHand;

public interface CardsParser<T> {
    PokerHand parse(T hand);
}
