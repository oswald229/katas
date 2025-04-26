package com.kata.pokerhands;

import com.kata.pokerhands.game.io.PokerHandReader;
import com.kata.pokerhands.game.model.Cards;
import com.kata.pokerhands.game.model.PokerHand;
import com.kata.pokerhands.game.model.PokerHandEnum;
import com.kata.pokerhands.game.rules.HandStrategy;
import com.kata.pokerhands.game.rules.HandStrategyRank;

import java.util.Arrays;

public class ConcretePokerHandReader implements PokerHandReader {

    @Override
    public PokerHandEnum tellHandFor(PokerHand pokerHand) {
        Cards cards = pokerHand.cards();
        return Arrays.stream(HandStrategyRank.values())
                .map(HandStrategyRank::getStrategy)
                .filter(handStrategy -> handStrategy.matches(cards))
                .findFirst()
                .map(HandStrategy::hand)
                .orElseThrow();
    }
}