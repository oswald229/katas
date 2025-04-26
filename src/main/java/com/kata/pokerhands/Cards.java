package com.kata.pokerhands;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Cards(List<Card> content) {
    Map<CardValue, Integer> groupedByCardValue() {
        return content
                .stream()
                .collect(Collectors.toMap(Card::getValue, card -> 1, Integer::sum));
    }
}
