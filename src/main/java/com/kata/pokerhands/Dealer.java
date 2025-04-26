package com.kata.pokerhands;

public interface Dealer {
    WinningHand getWinningHand(PokerHand blackHand, PokerHand whiteHand);

    WinningCard getWinningCard(PokerHand blackHand, PokerHand whiteHand);

    default Winner getWinner(PokerHand blackHand, PokerHand whiteHand) {
        return blackHand.equals(whiteHand)
                ? getWinningCard(blackHand, whiteHand)
                : getWinningHand(blackHand, whiteHand);
    }
}
