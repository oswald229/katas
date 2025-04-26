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
    public static final Card EMPTY = Card.builder().build();
    private Suit suit;
    private CardValue value;

    boolean isNeighbourOf(Card card) {
        return CardValue.indexOf(getValue()) - CardValue.indexOf(card.getValue()) == 1;
    }

    @Override
    public int compareTo(Card other) {
        if (this.value.equals(other.value))
            return 0;
        List<CardValue> cardValues = Arrays.stream(CardValue.values()).toList();
        return cardValues.indexOf(value) < cardValues.indexOf(other.value) ? -1 : 1;
    }

    public String cardString() {
        return getValue().toString() + getSuit().toString();
    }

    @Override
    public String toString() {
        return cardString();
    }

    public boolean isHigherThan(Card card) {
        return this.compareTo(card) > 0;
    }
}
