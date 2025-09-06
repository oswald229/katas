package com.kata.pokerhands.game.model;

public record HandWinner(String winner, PokerHandEnum hand) implements Winner {
}
