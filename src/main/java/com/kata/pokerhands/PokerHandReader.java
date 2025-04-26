package com.kata.pokerhands;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PokerHandReader {
    public static PokerHandEnum tellHandFor(PokerHand pokerHand) {


        List<Card> sortedHand = pokerHand.cards();
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


    private static int getCardIndexesDiff(List<Card> sortedHand, int i, int j) {
        return getEnumIndex(sortedHand.get(i).getValue()) - getEnumIndex(sortedHand.get(j).getValue());
    }

    private static int getEnumIndex(CardValue rootValue) {
        return Arrays.stream(CardValue.values()).toList().indexOf(rootValue);
    }

    public PokerHandEnum tellHand(String hand) {
        return new PokerHand(parseHand(hand)).tellHand();
    }

    static List<Card> parseHand(String hand) {
        List<Card> cards = PokerHandParser.mapHand(hand);
        return PokerHandParser.sortHand(cards);
    }

    static boolean isRoyalFlush(List<Card> cards) {
        return cards.get(0).getValue().equals(CardValue.ACE)
                && isFlushHand(cards)
                && isStraightHand(cards);
    }

    static boolean isPair(List<Card> cards) {
        Map<CardValue, Integer> handStats = getValueDistribution(cards);
        return handStats.size() == 4 && handStats.containsValue(2);
    }

    static boolean isTwoPair(List<Card> cards) {
        Map<CardValue, Integer> handStats = getValueDistribution(cards);
        return handStats.size() == 3 && handStats.containsValue(1);
    }

    static boolean isForOfAKind(List<Card> cards) {
        Map<CardValue, Integer> handStats = getValueDistribution(cards);
        return handStats.size() == 2 && handStats.containsValue(4);
    }

    static boolean isThreeOfAKind(List<Card> cards) {
        Map<CardValue, Integer> handStats = getValueDistribution(cards);
        return handStats.size() == 3 && handStats.containsValue(3) && handStats.containsValue(1);
    }

    static boolean isFullHouse(List<Card> cards) {
        Map<CardValue, Integer> handStats = getValueDistribution(cards);
        // A size 2 distribution means 3 and 2 distinct duplicates, hence a FullHouse.
        return handStats.size() == 2;
    }

    static boolean isFlushHand(List<Card> cards) {
        Suit suit = cards.get(0).getSuit();
        return cards.stream().filter(card -> card.getSuit().equals(suit))
                .count() == cards.size();
    }

    static boolean isStraightHand(List<Card> cards) {

        for (int i = 0, j = i + 1; j < cards.size(); i++, j++) {
            int diff = getCardIndexesDiff(cards, i, j);
            if (Math.abs(diff) != 1) {
                return false;
            }
        }
        return true;
    }

    public static Map<CardValue, Integer> getValueDistribution(List<Card> hand) {
        List<Card> sortedHand = PokerHandParser.sortHand(hand);
        EnumMap<CardValue, Integer> valueDistribution = new EnumMap<CardValue, Integer>(CardValue.class);

        for (Card card : sortedHand) {
            if (!valueDistribution.containsKey(card.getValue())) {
                valueDistribution.put(card.getValue(), 1);
                continue;
            }
            valueDistribution.merge(card.getValue(), 1, Integer::sum);
        }
        return valueDistribution;
    }
}