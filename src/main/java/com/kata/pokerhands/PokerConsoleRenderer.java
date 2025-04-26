package com.kata.pokerhands;

import com.kata.pokerhands.game.model.Winner;
import com.kata.pokerhands.game.io.CardRenderer;
import com.kata.pokerhands.game.io.HandRenderer;
import com.kata.pokerhands.game.io.PokerGameRenderer;
import com.kata.pokerhands.game.model.Card;
import com.kata.pokerhands.game.model.CardWinner;
import com.kata.pokerhands.game.model.HandWinner;

public record PokerConsoleRenderer(CardRenderer<String> cardRenderer,
                                   HandRenderer<String> handRenderer) implements PokerGameRenderer<String> {

    @Override
    public String printWinner(Winner winner) {
        if (winner.from().equals("CARD")) {
            return printCardWinner((CardWinner) winner);
        }
        return printHandWinner((HandWinner) winner);
    }

    private String printCardWinner(CardWinner cardWinner) {
        if (cardWinner.card().equals(Card.EMPTY)) {
            return "Tie";
        }
        return "%s wins - high card: %s".formatted(cardWinner.winner(),
                cardRenderer.render(cardWinner.card()));
    }

    private String printHandWinner(HandWinner handWinner) {
        return "%s wins - %s".formatted(handWinner.winner(), handRenderer.render(handWinner).toLowerCase()
                .replace("_", " "));
    }

}