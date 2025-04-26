package com.kata.pokerhands;

public interface PokerGameRenderer<T> {

    T printCardWinner(CardWinner cardWinner);

    T printHandWinner(HandWinner handWinner);

    T printWinner(Winner winner);
}
