package com.kata.pokerhands;

public class PokerConsolePrinter implements PokerPrinter {
    @Override
    public String printWinningCard(WinningCard winningCard) {
        if (winningCard.equals(WinningCard.EMPTY)) {
            return "Tie";
        }
        return printWinningCard(winningCard.winner(), winningCard.winningCard().getValue().toString());
    }

    @Override
    public String printWinningHand(WinningHand winningHand) {
        return "%s wins - %s".formatted(winningHand.winner(), winningHand.hand().toString().toLowerCase()
                .replace("_", " "));
    }

    @Override
    public String printWinner(Winner winner) {
        if (winner.from().equals("CARD")) {
            return printWinningCard((WinningCard) winner);
        }
        return printWinningHand((WinningHand) winner);
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