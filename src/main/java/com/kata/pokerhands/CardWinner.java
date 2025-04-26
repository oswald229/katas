package com.kata.pokerhands;

record CardWinner(String winner, Card card) implements Winner {
    static final CardWinner EMPTY = new CardWinner("", Card.EMPTY);

    @Override
    public String from() {
        return "CARD";
    }
}
