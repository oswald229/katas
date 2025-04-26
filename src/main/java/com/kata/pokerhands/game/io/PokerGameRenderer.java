package com.kata.pokerhands.game.io;

import com.kata.pokerhands.game.model.Winner;

public interface PokerGameRenderer<T> {

    T printWinner(Winner winner);
}
