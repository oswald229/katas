package com.kata.pokerhands;

public interface CardRenderer<T, U> {
    U render(T card);
}
