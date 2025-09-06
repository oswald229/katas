package com.kata.pokerhands.game.model;

public interface Winner {
    default String from() {
        return "HAND";
    }
}
