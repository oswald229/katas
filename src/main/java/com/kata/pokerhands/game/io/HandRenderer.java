package com.kata.pokerhands.game.io;

import com.kata.pokerhands.game.model.HandWinner;

public interface HandRenderer<T> {
    T render(HandWinner handWinner);
}
