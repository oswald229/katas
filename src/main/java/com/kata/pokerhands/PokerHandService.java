package com.kata.pokerhands;


import java.util.LinkedList;

public class PokerHandService {

    private static final String BLACK = "Black";
    private static final String WHITE = "White";
    private final PokerPrinter printer = new PokerConsolePrinter();

    public String winner(String black, String white) {
        var blackHand = new PokerHand(PokerHandReader.parseHand(black));
        var whiteHand = new PokerHand(PokerHandReader.parseHand(white));

        if (blackHand.equals(whiteHand)) {
            return winnerOnEqualHands(blackHand, whiteHand);
        }
        return printer.printWinner(getWinningHand(blackHand, whiteHand));
    }

    private WinningHand getWinningHand(PokerHand blackHandCards, PokerHand whiteHandCards) {
        var blackHand = blackHandCards.tellHand();
        var whiteHand = whiteHandCards.tellHand();

        return blackHand.strongerThan(whiteHand) ?
                new WinningHand(BLACK, blackHand)
                : new WinningHand(WHITE, whiteHand);
    }

    private String winnerOnEqualHands(PokerHand blackHandCards, PokerHand whiteHandCards) {
        WinningCard winningCard = getWinnerHighestCard(blackHandCards, whiteHandCards);
        if (winningCard.equals(WinningCard.EMPTY)) {
            return "Tie";
        }
        return printer.printWinningCard(winningCard);
    }

    private static WinningCard getWinnerHighestCard(PokerHand blackHandCards, PokerHand whiteHandCards) {
        return determineWinnerFromHighestCard(new LinkedList<>(blackHandCards.cards()), new LinkedList<>(whiteHandCards.cards()));
    }


    private static WinningCard determineWinnerFromHighestCard(LinkedList<Card> blackCards, LinkedList<Card> whiteCards) {
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
