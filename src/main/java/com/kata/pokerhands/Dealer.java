package com.kata.pokerhands;

public interface Dealer {

    Winner getWinner(PokerHand blackHand, PokerHand whiteHand);
}
