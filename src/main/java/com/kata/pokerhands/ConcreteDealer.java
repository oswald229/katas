package com.kata.pokerhands;

import java.util.LinkedList;

public record ConcreteDealer(PokerHandReader handReader) implements Dealer {
    static final String BLACK = "Black";
    static final String WHITE = "White";

    @Override
    public Winner getWinner(PokerHand blackHand, PokerHand whiteHand) {
        return handReader.areEquals(blackHand, whiteHand)
                ? getWinningCard(blackHand, whiteHand)
                : getWinningHand(blackHand, whiteHand);
    }

    private WinningHand getWinningHand(PokerHand blackHand, PokerHand whiteHand) {

        PokerHandEnum pokerHandEnum = handReader.tellHandFor(blackHand);
        PokerHandEnum hand = handReader.tellHandFor(whiteHand);
        return pokerHandEnum.strongerThan(
                hand
        )
                ? new WinningHand(BLACK, pokerHandEnum)
                : new WinningHand(WHITE, hand);
    }

    private WinningCard getWinningCard(PokerHand blackHand, PokerHand whiteHand) {
        return determineWinnerFromHighestCard(new LinkedList<>(blackHand.cards()), new LinkedList<>(whiteHand.cards()));
    }

    private WinningCard determineWinnerFromHighestCard(LinkedList<Card> blackCards, LinkedList<Card> whiteCards) {
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