package com.kata.pokerhands;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PokerHandService {

    private static final String BLACK = "Black";
    private static final String WHITE = "White";
    private final PokerHandReader pokerHandReader = new PokerHandReader();
    private final PokerHandComparator pokerHandComparator = new PokerHandComparator();
    private final PokerPrinter printer = new PokerConsolePrinter();

    public PokerHandEnum tellHand(String hand) {
        return pokerHandReader.tellHand(hand);
    }

    public Map<CardValue, Integer> getValueDistribution(List<Card> hand) {
        return pokerHandReader.getValueDistribution(hand);
    }

    public PokerHandEnum higherHand(PokerHandEnum hand1, PokerHandEnum hand2) {
        if (pokerHandComparator.compare(hand1, hand2) > 0) {
            return hand1;
        }
        return hand2;
    }

    public String winner(String black, String white) {

        PokerHand blackHandCards = new PokerHand(PokerHandReader.parseHand(black));
        PokerHand whiteHandCards = new PokerHand(PokerHandReader.parseHand(white));

        PokerHandEnum blackHand = pokerHandReader.tellHand(blackHandCards);
        PokerHandEnum whiteHand = pokerHandReader.tellHand(whiteHandCards);

        if (whiteHand.equals(blackHand)) {
            return winnerOnEqualHands(blackHandCards, whiteHandCards);

        }
        PokerHandEnum higherHand = higherHand(blackHand, whiteHand);
        if (higherHand.equals(blackHand)) {
            return printer.printWinner(BLACK, higherHand.toString().toLowerCase());
        }
        return printer.printWinner(WHITE, higherHand.toString().toLowerCase());
    }

    private String winnerOnEqualHands(PokerHand blackHandCards, PokerHand whiteHandCards) {
        WinningCard winningCard = getWinnerHighestCard(blackHandCards, whiteHandCards);
        if (winningCard.equals(WinningCard.EMPTY)) {
            return "Tie";
        }
        return printer.printWinningCard(winningCard);
    }

    private static WinningCard getWinnerHighestCard(PokerHand blackHandCards, PokerHand whiteHandCards) {
        return determineWinner(new LinkedList<>(blackHandCards.cards()), new LinkedList<>(whiteHandCards.cards()));
    }


    private static WinningCard determineWinner(LinkedList<Card> blackCards, LinkedList<Card> whiteCards) {
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
        return determineWinner(blackCards, whiteCards);
    }

}
