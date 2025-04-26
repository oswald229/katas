package com.kata.pokerhands;

import java.util.LinkedList;

public class Dealer {
    static final String BLACK = "Black";
    static final String WHITE = "White";

    WinningHand getWinningHand(PokerHand blackHand, PokerHand whiteHand) {
        return blackHand.strongerThan(whiteHand)
                ? new WinningHand(BLACK, blackHand)
                : new WinningHand(WHITE, whiteHand);
    }

    static WinningCard getWinningCard(PokerHand blackHandCards, PokerHand whiteHandCards) {
        return determineWinnerFromHighestCard(new LinkedList<>(blackHandCards.cards()), new LinkedList<>(whiteHandCards.cards()));
    }

    static WinningCard determineWinnerFromHighestCard(LinkedList<Card> blackCards, LinkedList<Card> whiteCards) {
        if (blackCards.isEmpty()) {
            return WinningCard.EMPTY;
        }
        var blackCard = blackCards.removeFirst();
        var whiteCard = whiteCards.removeFirst();
        if (blackCard.isHigherThan(whiteCard)) {
            return new WinningCard(BLACK, blackCard);
        }
        if (whiteCard.isHigherThan(blackCard)) {
            return new WinningCard(WHITE, whiteCard);
        }
        return determineWinnerFromHighestCard(blackCards, whiteCards);
    }
}