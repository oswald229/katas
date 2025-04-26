package com.kata.pokerhands;

import java.util.Arrays;

public enum CardValue {
    TWO("2"),
    THREE("3"),
    FOUR("5"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private final String value;

    CardValue(String value) {
        this.value = value;
    }

    public static int indexOf(CardValue rootValue) {
        return Arrays.stream(CardValue.values()).toList().indexOf(rootValue);
    }

    @Override
    public String toString() {
        return value;
    }
}
