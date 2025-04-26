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

    private HandWinner getWinningHand(PokerHand blackHand, PokerHand whiteHand) {

        PokerHandEnum pokerHandEnum = handReader.tellHandFor(blackHand);
        PokerHandEnum hand = handReader.tellHandFor(whiteHand);
        return pokerHandEnum.strongerThan(
                hand
        )
                ? new HandWinner(BLACK, pokerHandEnum)
                : new HandWinner(WHITE, hand);
    }

    private CardWinner getWinningCard(PokerHand blackHand, PokerHand whiteHand) {
        return determineWinnerFromHighestCard(new LinkedList<>(blackHand.cards().content()), new LinkedList<>(whiteHand.cards().content()));
    }

    private CardWinner determineWinnerFromHighestCard(LinkedList<Card> blackCards, LinkedList<Card> whiteCards) {
        if (blackCards.isEmpty()) {
            return CardWinner.EMPTY;
        }
        var blackCard = blackCards.removeFirst();
        var whiteCard = whiteCards.removeFirst();
        if (blackCard.isHigherThan(whiteCard)) {
            return new CardWinner(BLACK, blackCard);
        }
        if (whiteCard.isHigherThan(blackCard)) {
            return new CardWinner(WHITE, whiteCard);
        }
        return determineWinnerFromHighestCard(blackCards, whiteCards);
    }
}