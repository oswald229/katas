package com.kata.pokerhands;

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