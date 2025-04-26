package com.kata.pokerhands;

public interface PokerGameRenderer<T> {

    T printWinningCard(CardWinner cardWinner);

    T printWinningHand(HandWinner handWinner);

    T printWinner(Winner winner);
}
