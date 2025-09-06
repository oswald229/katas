package com.kata.pokerhands.game.model;

public enum Suit {
    CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S");

    private final String value;

    Suit(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
