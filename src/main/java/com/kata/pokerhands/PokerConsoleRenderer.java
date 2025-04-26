package com.kata.pokerhands;

public record PokerConsoleRenderer(CardRenderer<String, String> cardRenderer) implements PokerGameRenderer<String> {

    @Override
    public String printWinner(Winner winner) {
        if (winner.from().equals("CARD")) {
            return printWinningCard((WinningCard) winner);
        }
        return printWinningHand((WinningHand) winner);
    }

    @Override
    public String printWinningCard(WinningCard winningCard) {
        if (winningCard.equals(WinningCard.EMPTY)) {
            return "Tie";
        }
        return "%s wins - high card: %s".formatted(winningCard.winner(),
                cardRenderer.render(winningCard.card().getValue().toString()));
    }

    @Override
    public String printWinningHand(WinningHand winningHand) {
        return "%s wins - %s".formatted(winningHand.winner(), winningHand.hand().toString().toLowerCase()
                .replace("_", " "));
    }

}