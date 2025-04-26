package com.kata.pokerhands;

public record WinningHand(String winner, PokerHandEnum hand) implements Winner {
}
