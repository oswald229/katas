package com.kata.pokerhands;

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
    JACK("Jack"),
    QUEEN("Queen"),
    KING("King"),
    ACE("Ace");

    private final String value;

    CardValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
