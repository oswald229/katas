package com.kata.pokerhands;

public interface PokerPrinter {

    String printWinningCard(WinningCard winningCard);

    String printWinningHand(WinningHand winningHand);

    String printWinner(Winner winner);
}
