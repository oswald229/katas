package com.kata.pokerhands;

public interface CardParser<T> {
    Cards parse(T hand);
}
