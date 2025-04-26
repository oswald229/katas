package com.kata.pokerhands;

public interface Winner {
    default String from() {
        return "HAND";
    }
}
