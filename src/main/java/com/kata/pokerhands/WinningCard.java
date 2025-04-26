package com.kata.pokerhands;

record WinningCard(String winner, Card winningCard) implements Winner {
    static final WinningCard EMPTY = new WinningCard("", Card.builder().build());
}
