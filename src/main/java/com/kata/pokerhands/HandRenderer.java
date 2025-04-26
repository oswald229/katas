package com.kata.pokerhands;

public interface HandRenderer<T> {
    T render(HandWinner handWinner);
}
