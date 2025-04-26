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
            var winningCard = getWinningCard(blackHand, whiteHand);
            return printer.printWinningCard(winningCard);
        }
        var winningHand = getWinningHand(blackHand, whiteHand);
        return printer.printWinningHand(winningHand);
    }

    private WinningHand getWinningHand(PokerHand blackHand, PokerHand whiteHand) {
        return blackHand.strongerThan(whiteHand)
                ? new WinningHand(BLACK, blackHand)
                : new WinningHand(WHITE, whiteHand);
    }

    private static WinningCard getWinningCard(PokerHand blackHandCards, PokerHand whiteHandCards) {
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
