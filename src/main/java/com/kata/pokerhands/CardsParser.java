package com.kata.pokerhands;

public interface CardsParser<T> {
    PokerHand parse(T hand);
}
