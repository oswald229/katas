package com.kata.pokerhands;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PokerHandReader {
    private PokerHandReader() {
    }

    private static final TreeMap<Integer, HandStrategy> handStrategyPriority = new TreeMap<>();

    static {
        handStrategyPriority.put(0, new RoyalFlushHandStrategy());
        handStrategyPriority.put(1, new StraightFlushStrategy());
        handStrategyPriority.put(2, new ForOfAKindStrategy());
        handStrategyPriority.put(3, new FullHouseHandStrategy());
        handStrategyPriority.put(4, new FlushHandStrategy());
        handStrategyPriority.put(5, new StraightHandStrategy());
        handStrategyPriority.put(6, new ThreeOfAKindHandStrategy());
        handStrategyPriority.put(7, new TwoPairHandStrategy());
        handStrategyPriority.put(8, new PairHandStrategy());
        handStrategyPriority.put(9, cards -> true);
    }

    public static PokerHandEnum tellHandFor(PokerHand pokerHand) {
        var cards = pokerHand.cards();
        return handStrategyPriority.values()
                .stream()
                .filter(handStrategy -> handStrategy.matches(cards))
                .findFirst()
                .map(HandStrategy::hand)
                .orElseThrow();
    }


    static List<Card> parseHand(String hand) {
        List<Card> cards = PokerHandParser.mapHand(hand);
        return PokerHandParser.sortHand(cards);
    }

    public static Map<CardValue, Integer> groupByCard(List<Card> cards) {
        return cards
                .stream()
                .collect(Collectors.toMap(Card::getValue, card -> 1, Integer::sum));
    }
}