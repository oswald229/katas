package com.kata.pokerhands;

public interface Dealer {
    WinningHand getWinningHand(PokerHand blackHand, PokerHand whiteHand);

    WinningCard getWinningCard(PokerHand blackHandCards, PokerHand whiteHandCards);
}
