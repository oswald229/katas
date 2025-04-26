package com.kata.pokerhands;

import com.kata.pokerhands.game.io.HandRenderer;
import com.kata.pokerhands.game.model.HandWinner;

class PlainTextHandRenderer implements HandRenderer<String> {
    @Override
    public String render(HandWinner handWinner) {
        return handWinner.hand().toString();
    }
}
