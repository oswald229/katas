package com.kata.pokerhands;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PokerHandReader {


    private static final TreeMap<Integer, HandStrategy> handStrategyPriority = new TreeMap<>();

    static {
        handStrategyPriority.put(0, new HandStrategy(PokerHandReader::isRoyalFlush, PokerHandEnum.ROYAL_FLUSH));
        handStrategyPriority.put(1, new HandStrategy(cards -> isStraightHand(new LinkedList<>(cards)) && isFlushHand(cards), PokerHandEnum.STRAIGHT_FLUSH));
        handStrategyPriority.put(2, new HandStrategy(PokerHandReader::isForOfAKind, PokerHandEnum.FOUR_OF_A_KIND));
        handStrategyPriority.put(3, new HandStrategy(PokerHandReader::isFullHouse, PokerHandEnum.FULL_HOUSE));
        handStrategyPriority.put(4, new HandStrategy(PokerHandReader::isFlushHand, PokerHandEnum.FLUSH));
        handStrategyPriority.put(5, new HandStrategy(cards -> isStraightHand(new LinkedList<>(cards)), PokerHandEnum.STRAIGHT));
        handStrategyPriority.put(6, new HandStrategy(PokerHandReader::isThreeOfAKind, PokerHandEnum.THREE_OF_A_KIND));
        handStrategyPriority.put(7, new HandStrategy(PokerHandReader::isTwoPair, PokerHandEnum.TWO_PAIR));
        handStrategyPriority.put(8, new HandStrategy(PokerHandReader::isPair, PokerHandEnum.PAIR));
    }

    record HandStrategy(Function<List<Card>, Boolean> key, PokerHandEnum hand) {
    }

    public static PokerHandEnum tellHandFor(PokerHand pokerHand) {
        var cards = pokerHand.cards();
        for (HandStrategy handStrategy : handStrategyPriority.values()) {
            if (handStrategy.key().apply(cards)) {
                return handStrategy.hand();
            }
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