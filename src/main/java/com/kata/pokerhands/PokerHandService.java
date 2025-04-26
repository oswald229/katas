package com.kata.pokerhands;


import java.util.List;
import java.util.Map;

public class PokerHandService {

    private static final String BLACK = "Black";
    private static final String WHITE = "White";
    private final PokerHandReader pokerHandReader = new PokerHandReader();
    private final PokerHandComparator pokerHandComparator = new PokerHandComparator();

    public PokerHandEnum tellHand(String hand) {
        return pokerHandReader.tellHand(hand);
    }

    public Map<CardValue, Integer> getValueDistribution(List<Card> hand) {
        return pokerHandReader.getValueDistribution(hand);
    }

    public PokerHandEnum higherHand(PokerHandEnum hand1, PokerHandEnum hand2) {
        if (pokerHandComparator.compare(hand1, hand2) > 0){
            return hand1;
        }
        return hand2;
    }

    public String winner(String black, String white) {

        List<Card> blackHandCards = PokerHandReader.parseHand(black);
        List<Card> whiteHandCards = PokerHandReader.parseHand(white);

        PokerHandEnum blackHand = pokerHandReader.tellHand(blackHandCards);
        PokerHandEnum whiteHand = pokerHandReader.tellHand(whiteHandCards);

        if (whiteHand.equals(blackHand)) {

            Map<String, String> winnerHighestCard = getWinnerHighestCard(blackHandCards, whiteHandCards);
            if (winnerHighestCard.isEmpty()) {
                return "Tie";
            }
            if (winnerHighestCard.containsKey(BLACK)) {
                return printHighCardWinner(BLACK, winnerHighestCard.get(BLACK));
            }
            return printHighCardWinner(WHITE, winnerHighestCard.get(WHITE));

        } else {
            PokerHandEnum higherHand = higherHand(blackHand, whiteHand);

            if (higherHand.equals(blackHand)) {
                return printWinner(BLACK, higherHand.toString().toLowerCase());
            }

            return printWinner(WHITE, higherHand.toString().toLowerCase());
        }

    }

    private static String printWinner(String winner, String higherHand) {
        return "%s wins - %s".formatted(winner, higherHand.replace("_", " "));
    }

    private static String printHighCardWinner(String winner, String winningCard) {
        winningCard = switch (winningCard) {
            case "A" -> "Ace";
            case "J" -> "Jack";
            case "Q" -> "Queen";
            case "K" -> "King";
            default -> winningCard;
        };
        return "%s wins - high card: %s".formatted(winner, winningCard);
    }

    private static Map<String, String> getWinnerHighestCard(List<Card> blackHandCards, List<Card> whiteHandCards) {

        for (int i = 0; i < 5; i++) {
            if (blackHandCards.get(i).compareTo(whiteHandCards.get(i)) > 0) {
                return Map.of(BLACK, blackHandCards.get(i).getValue().toString());
            } else if (blackHandCards.get(i).compareTo(whiteHandCards.get(i)) < 0) {
                return Map.of(WHITE, whiteHandCards.get(i).getValue().toString());
            }
        }
        return Map.of();
    }

}
