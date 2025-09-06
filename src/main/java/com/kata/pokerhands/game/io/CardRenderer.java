package com.kata.pokerhands.game.io;

import com.kata.pokerhands.game.model.Card;

public interface CardRenderer<U> {
    U render(Card card);
}
