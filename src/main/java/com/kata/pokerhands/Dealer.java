package com.kata.pokerhands;

public interface Dealer {
    WinningHand getWinningHand(PokerHand blackHand, PokerHand whiteHand);

    WinningCard getWinningCard(PokerHand blackHandCards, PokerHand whiteHandCards);

    default Winner getWinner(PokerHand blackHand, PokerHand whiteHand) {
        Winner winner = getWinningHand(blackHand, whiteHand);
        if (blackHand.equals(whiteHand)) {
            winner = getWinningCard(blackHand, whiteHand);
        }
        return winner;
    }
}
