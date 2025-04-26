package com.kata.pokerhands;


import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PokerHandService {

    private static final String BLACK = "Black";
    private static final String WHITE = "White";
    private static final int POKER_HAND_SIZE = 5;
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
        Pair<String, String> winnerHighestCard = getWinnerHighestCard(blackHandCards, whiteHandCards);
        if (winnerHighestCard.getLeft().equals("")) {
            return "Tie";
        }
        return printer.printHighCardWinner(winnerHighestCard.getLeft(), winnerHighestCard.getRight());
    }

    private static Pair<String, String> getWinnerHighestCard(PokerHand blackHandCards, PokerHand whiteHandCards) {
        return determineWinner(new LinkedList<>(blackHandCards.cards()), new LinkedList<>(whiteHandCards.cards()));
    }


    private static Pair<String, String> determineWinner(LinkedList<Card> blackCards, LinkedList<Card> whiteCards) {
        if (blackCards.isEmpty()) {
            return Pair.of("", "");
        }
        var blackCard = blackCards.removeFirst();
        var whiteCard = whiteCards.removeFirst();
        if (blackCard.isHigherThan(whiteCard)) {
            return Pair.of(BLACK, blackCard.getValue().toString());
        }
        if (whiteCard.isHigherThan(blackCard)) {
            return Pair.of(WHITE, whiteCard.getValue().toString());
        }
        return determineWinner(blackCards, whiteCards);
    }

}
