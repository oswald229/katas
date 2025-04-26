package com.kata.pokerhands;

public class PokerConsolePrinter implements PokerPrinter {
    @Override
    public String printWinningCard(WinningCard winningCard) {
        return printWinningCard(winningCard.winner(), winningCard.winningCard().getValue().toString());
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

    @Override
    public String printWinner(String winner, String higherHand) {
        return "%s wins - %s".formatted(winner, higherHand.replace("_", " "));
    }
}