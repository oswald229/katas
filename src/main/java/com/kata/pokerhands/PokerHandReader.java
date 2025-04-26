package com.kata.pokerhands;

import java.util.TreeMap;

public class PokerHandReader {
    private PokerHandReader() {
    }

    private static final TreeMap<Integer, HandStrategy> handStrategyPriority = new TreeMap<>();

    public static PokerHandEnum tellHandFor(PokerHand pokerHand) {
        var cards = pokerHand.cards();
        return handStrategyPriority.values()
                .stream()
                .filter(handStrategy -> handStrategy.matches(new Cards(cards)))
                .findFirst()
                .map(HandStrategy::hand)
                .orElseThrow();
    }


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

}