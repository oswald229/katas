package com.kata.pokerhands.game.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Cards(List<Card> content) {
    public Map<CardValue, Integer> groupedByCardValue() {
        return content
                .stream()
                .collect(Collectors.toMap(Card::getValue, card -> 1, Integer::sum));
    }
}
