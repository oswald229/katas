package com.kata.pokerhands;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Builder
@EqualsAndHashCode
@Getter
public class Card implements Comparable<Card> {
    private Suit suit;
    private CardValue value;

    @Override
    public int compareTo(Card other) {
        if (this.value.equals(other.value))
            return 0;
        List<CardValue> cardValues = Arrays.stream(CardValue.values()).toList();
        return cardValues.indexOf(value) < cardValues.indexOf(other.value) ? -1 : 1;
    }
}
