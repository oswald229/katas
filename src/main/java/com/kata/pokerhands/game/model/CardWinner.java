package com.kata.pokerhands.game.model;

public record CardWinner(String winner, Card card) implements Winner {
    public static final CardWinner EMPTY = new CardWinner("", Card.EMPTY);

    @Override
    public String from() {
        return "CARD";
    }
}
