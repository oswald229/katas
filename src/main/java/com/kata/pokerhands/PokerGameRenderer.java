package com.kata.pokerhands;

public interface PokerGameRenderer<T> {

    T printWinningCard(WinningCard winningCard);

    T printWinningHand(WinningHand winningHand);

    T printWinner(Winner winner);
}
