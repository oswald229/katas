package com.kata.pokerhands;


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
        Map<String, String> winnerHighestCard = getWinnerHighestCard(blackHandCards, whiteHandCards);
        if (winnerHighestCard.isEmpty()) {
            return "Tie";
        }
        if (winnerHighestCard.containsKey(BLACK)) {
            return printer.printHighCardWinner(BLACK, winnerHighestCard.get(BLACK));
        }
        return printer.printHighCardWinner(WHITE, winnerHighestCard.get(WHITE));
    }

    private static Map<String, String> getWinnerHighestCard(PokerHand blackHandCards, PokerHand whiteHandCards) {

        for (int i = 0; i < POKER_HAND_SIZE; i++) {
            if (blackHandCards.cards().get(i).compareTo(whiteHandCards.cards().get(i)) > 0) {
                return Map.of(BLACK, blackHandCards.cards().get(i).getValue().toString());
            } else if (blackHandCards.cards().get(i).compareTo(whiteHandCards.cards().get(i)) < 0) {
                return Map.of(WHITE, whiteHandCards.cards().get(i).getValue().toString());
            }
        }
        return Map.of();
    }

}
