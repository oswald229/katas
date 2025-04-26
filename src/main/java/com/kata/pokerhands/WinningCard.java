package com.kata.pokerhands;

record WinningCard(String winner, Card card) implements Winner {
    static final WinningCard EMPTY = new WinningCard("", Card.builder().build());

    @Override
    public String from() {
        return "CARD";
    }
}
