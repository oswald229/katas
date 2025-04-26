package com.kata.pokerhands;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        var groupedCard = groupByCard(cards);
        return groupedCard.size() == 4 && groupedCard.containsValue(2);
    }

    static boolean isTwoPair(List<Card> cards) {
        var groupedCard = groupByCard(cards);
        return groupedCard.size() == 3 && groupedCard.containsValue(1);
    }

    static boolean isForOfAKind(List<Card> cards) {
        var groupedCard = groupByCard(cards);
        return groupedCard.size() == 2 && groupedCard.containsValue(4);
    }

    static boolean isThreeOfAKind(List<Card> cards) {
        var groupedCard = groupByCard(cards);
        return groupedCard.size() == 3 && groupedCard.containsValue(3) && groupedCard.containsValue(1);
    }

    static boolean isFullHouse(List<Card> cards) {
        var groupedCard = groupByCard(cards);
        // A size 2 distribution means 3 and 2 distinct duplicates, hence a FullHouse.
        return groupedCard.size() == 2;
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

    private static Map<CardValue, Integer> groupByCard(List<Card> cards) {
        return cards
                .stream()
                .collect(Collectors.toMap(Card::getValue, card -> 1, Integer::sum));
    }
}