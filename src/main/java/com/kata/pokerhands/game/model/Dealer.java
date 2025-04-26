package com.kata.pokerhands.game.model;

public interface Dealer {

    Winner getWinner(PokerHand blackHand, PokerHand whiteHand);
}
