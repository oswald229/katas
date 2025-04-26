package com.kata.pokerhands;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PokerHandService {

    private static final String BLACK = "Black";
    private static final String WHITE = "White";
    private final PokerHandReader pokerHandReader = new PokerHandReader();
    private final PokerPrinter printer = new PokerConsolePrinter();

    public PokerHandEnum tellHand(String hand) {
        return pokerHandReader.tellHand(hand);
    }

    public Map<CardValue, Integer> getValueDistribution(List<Card> hand) {
        return pokerHandReader.getValueDistribution(hand);
    }

    public String winner(String black, String white) {

        PokerHand blackHandCards = new PokerHand(PokerHandReader.parseHand(black));
        PokerHand whiteHandCards = new PokerHand(PokerHandReader.parseHand(white));

        PokerHandEnum blackHand = pokerHandReader.tellHand(blackHandCards);
        PokerHandEnum whiteHand = pokerHandReader.tellHand(whiteHandCards);

        if (whiteHand.equals(blackHand)) {
            return winnerOnEqualHands(blackHandCards, whiteHandCards);
        }
        return printer.printWinner(getWinningHand(blackHand, whiteHand));
    }

    private WinningHand getWinningHand(PokerHandEnum blackHand, PokerHandEnum whiteHand) {
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
