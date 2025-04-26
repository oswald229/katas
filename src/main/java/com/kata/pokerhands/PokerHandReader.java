package com.kata.pokerhands;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class PokerHandReader {
    public static PokerHandEnum tellHandFor(PokerHand pokerHand) {
        var handCards = pokerHand.cards();
        if (isRoyalFlush(handCards)) {
            return PokerHandEnum.ROYAL_FLUSH;
        }

        if (isStraightHand(new LinkedList<>(handCards)) && isFlushHand(handCards)) {
            return PokerHandEnum.STRAIGHT_FLUSH;
        }

        if (isForOfAKind(handCards)) {
            return PokerHandEnum.FOUR_OF_A_KIND;
        }

        if (isFullHouse(handCards)) {
            return PokerHandEnum.FULL_HOUSE;
        }

        if (isFlushHand(handCards)) {
            return PokerHandEnum.FLUSH;
        }

        if (isStraightHand(new LinkedList<>(handCards))) {
            return PokerHandEnum.STRAIGHT;
        }

        if (isThreeOfAKind(handCards)) {
            return PokerHandEnum.THREE_OF_A_KIND;
        }

        if (isTwoPair(handCards)) {
            return PokerHandEnum.TWO_PAIR;
        }

        if (isPair(handCards)) {
            return PokerHandEnum.PAIR;
        }

        return PokerHandEnum.HIGH_CARD;
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
                && isStraightHand(new LinkedList<>(cards));
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

    static boolean isStraightHand(LinkedList<Card> cards) {
        if (cards.size() == 2) {
            return cards.get(0).isNeighbourOf(cards.get(1));
        }
        var headCard = cards.pop();
        var nextCard = cards.peek();

        if (Objects.nonNull(headCard) && Objects.nonNull(nextCard) && headCard.isNeighbourOf(nextCard)) {
            return isStraightHand(cards);
        }
        return false;
    }

    private static Map<CardValue, Integer> groupByCard(List<Card> cards) {
        return cards
                .stream()
                .collect(Collectors.toMap(Card::getValue, card -> 1, Integer::sum));
    }
}