package com.kata.pokerhands;

public class PokerConsolePrinter implements PokerPrinter {
    @Override
    public String printWinningCard(WinningCard winningCard) {
        return printWinningCard(winningCard.winner(), winningCard.winningCard().getValue().toString());
    }

    @Override
    public String printWinner(WinningHand winningHand) {
        return "%s wins - %s".formatted(winningHand.winner(), winningHand.hand().toString().toLowerCase()
                .replace("_", " "));
    }

    private String printWinningCard(String winner, String winningCard) {
        winningCard = switch (winningCard) {
            case "A" -> "Ace";
            case "J" -> "Jack";
            case "Q" -> "Queen";
            case "K" -> "King";
            default -> winningCard;
        };
        return "%s wins - high card: %s".formatted(winner, winningCard);
    }

}