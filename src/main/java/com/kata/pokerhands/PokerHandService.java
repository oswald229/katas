package com.kata.pokerhands;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PokerHandService {

    private static final String BLACK = "black";
    private static final String WHITE = "white";

    public PokerHandEnum tellHand(String hand) {
        List<Card> sortedHand = parseHand(hand);
        return tellHand(sortedHand);
    }

    private static List<Card> parseHand(String hand) {
        List<Card> cards = PokerHandParser.mapHand(hand);
        return PokerHandParser.sortHand(cards);
    }

    private PokerHandEnum tellHand(List<Card> sortedHand) {

        if (isRoyalFlush(sortedHand)) {
            return PokerHandEnum.ROYAL_FLUSH;
        }

        if (isStraightHand(sortedHand) && isFlushHand(sortedHand)) {
            return PokerHandEnum.STRAIGHT_FLUSH;
        }

        if (isForOfAKind(sortedHand)) {
            return PokerHandEnum.FOUR_OF_A_KIND;
        }

        if (isFullHouse(sortedHand)) {
            return PokerHandEnum.FULL_HOUSE;
        }

        if (isFlushHand(sortedHand)) {
            return PokerHandEnum.FLUSH;
        }

        if (isStraightHand(sortedHand)) {
            return PokerHandEnum.STRAIGHT;
        }

        if (isThreeOfAKind(sortedHand)) {
            return PokerHandEnum.THREE_OF_A_KIND;
        }

        if (isTwoPair(sortedHand)) {
            return PokerHandEnum.TWO_PAIR;
        }

        if (isPair(sortedHand)) {
            return PokerHandEnum.PAIR;
        }

        return PokerHandEnum.HIGH_CARD;
    }

    private boolean isRoyalFlush(List<Card> cards) {
        return cards.get(0).getValue().equals(CardValue.ACE)
                && isFlushHand(cards)
                && isStraightHand(cards);
    }

    private boolean isPair(List<Card> cards) {
        Map<CardValue, Integer> handStats = getValueDistribution(cards);
        return handStats.size() == 4 && handStats.containsValue(2);
    }

    private boolean isTwoPair(List<Card> cards) {
        Map<CardValue, Integer> handStats = getValueDistribution(cards);
        return handStats.size() == 3 && handStats.containsValue(1);
    }

    private boolean isForOfAKind(List<Card> cards) {
        Map<CardValue, Integer> handStats = getValueDistribution(cards);
        return handStats.size() == 2 && handStats.containsValue(4);
    }

    private boolean isThreeOfAKind(List<Card> cards) {
        Map<CardValue, Integer> handStats = getValueDistribution(cards);
        return handStats.size() == 3 && handStats.containsValue(3) && handStats.containsValue(1);
    }

    private boolean isFullHouse(List<Card> cards) {
        Map<CardValue, Integer> handStats = getValueDistribution(cards);
        // A size 2 distribution means 3 and 2 distinct duplicates, hence a FullHouse.
        return handStats.size() == 2;
    }

    private boolean isFlushHand(List<Card> cards) {
        Suit suit = cards.get(0).getSuit();
        return cards.stream().filter(card -> card.getSuit().equals(suit))
                .count() == cards.size();
    }

    private boolean isStraightHand(List<Card> cards) {

        for (int i = 0, j = i + 1; j < cards.size(); i++, j++) {
            int diff = getCardIndexesDiff(cards, i, j);
            if (Math.abs(diff) != 1) {
                return false;
            }
        }
        return true;
    }

    private static int getCardIndexesDiff(List<Card> sortedHand, int i, int j) {
        return getEnumIndex(sortedHand.get(i).getValue()) - getEnumIndex(sortedHand.get(j).getValue());
    }

    private static int getEnumIndex(CardValue rootValue) {
        return Arrays.stream(CardValue.values()).toList().indexOf(rootValue);
    }

    public Map<CardValue, Integer> getValueDistribution(List<Card> hand) {
        List<Card> sortedHand = PokerHandParser.sortHand(hand);
        EnumMap<CardValue, Integer> valueDistribution = new EnumMap<>(CardValue.class);

        for (Card card : sortedHand) {
            if (!valueDistribution.containsKey(card.getValue())) {
                valueDistribution.put(card.getValue(), 1);
                continue;
            }
            valueDistribution.merge(card.getValue(), 1, Integer::sum);
        }
        return valueDistribution;
    }

    public PokerHandEnum higherHand(PokerHandEnum hand1, PokerHandEnum hand2) {

        List<PokerHandEnum> pokerHandEnums = Arrays.stream(PokerHandEnum.values()).toList();

        return pokerHandEnums.indexOf(hand1) > pokerHandEnums.indexOf(hand2) ? hand1 : hand2;
    }

    public String winner(String black, String white) {

        List<Card> blackHandCards = parseHand(black);
        List<Card> whiteHandCards = parseHand(white);

        PokerHandEnum blackHand = tellHand(blackHandCards);
        PokerHandEnum whiteHand = tellHand(whiteHandCards);

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
        return "%s wins - %s".formatted(StringUtils.capitalize(winner), higherHand.replace("_", " "));
    }

    private static String printHighCardWinner(String winner, String winningCard) {
        return "%s wins - high card: %s".formatted(StringUtils.capitalize(winner), winningCard);
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
