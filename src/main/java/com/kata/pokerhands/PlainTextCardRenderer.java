package com.kata.pokerhands;

import com.kata.pokerhands.game.io.CardRenderer;
import com.kata.pokerhands.game.model.Card;

class PlainTextCardRenderer implements CardRenderer<String> {
    @Override
    public String render(Card card) {
        if (card.equals(Card.EMPTY)) {
            return "";
        }
        return switch (card.getValue().toString()) {
            case "A" -> "Ace";
            case "J" -> "Jack";
            case "Q" -> "Queen";
            case "K" -> "King";
            default -> card.getValue().toString();
        };
    }
}
