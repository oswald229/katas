package com.kata.pokerhands;

record WinningCard(String winner, Card winningCard) {
    static final WinningCard EMPTY = new WinningCard("", Card.builder().build());
}
