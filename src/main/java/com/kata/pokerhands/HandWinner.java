package com.kata.pokerhands;

public record HandWinner(String winner, PokerHandEnum hand) implements Winner {
}
