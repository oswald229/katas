package com.kata.pokerhands.game.rules;

public enum HandStrategyRank {
    ROYAL_FLUSH(new RoyalFlushHandStrategy()),
    STRAIGHT_FLUSH(new StraightFlushStrategy()),
    FOUR_OF_A_KIND(new ForOfAKindStrategy()),
    FULL_HOUSE(new FullHouseHandStrategy()),
    FLUSH(new FlushHandStrategy()),
    STRAIGHT(new StraightHandStrategy()),
    THREE_OF_A_KIND(new ThreeOfAKindHandStrategy()),
    TWO_PAIR(new TwoPairHandStrategy()),
    PAIR(new PairHandStrategy()),
    HIGH_CARD(cards -> true);

    private final HandStrategy strategy;

    HandStrategyRank(HandStrategy strategy) {
        this.strategy = strategy;
    }

    public HandStrategy getStrategy() {
        return strategy;
    }
}
