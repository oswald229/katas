package com.kata.pokerhands;

public record PokerConsoleRenderer(CardRenderer<String> cardRenderer) implements PokerGameRenderer<String> {

    @Override
    public String printWinner(Winner winner) {
        if (winner.from().equals("CARD")) {
            return printWinningCard((CardWinner) winner);
        }
        return printWinningHand((HandWinner) winner);
    }

    @Override
    public String printWinningCard(CardWinner cardWinner) {
        if (cardWinner.card().equals(Card.EMPTY)) {
            return "Tie";
        }
        return "%s wins - high card: %s".formatted(cardWinner.winner(),
                cardRenderer.render(cardWinner.card()));
    }

    @Override
    public String printWinningHand(HandWinner handWinner) {
        return "%s wins - %s".formatted(handWinner.winner(), handWinner.hand().toString().toLowerCase()
                .replace("_", " "));
    }

}