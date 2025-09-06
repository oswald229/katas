package com.kata.fizzbuzz;

import java.util.function.IntFunction;

public class FizzBuzz {

    private final String value;

    FizzBuzz(int value){
        this.value = fizzBuzzFunction.apply(value);
    }

    @Override
    public String toString() {
        return this.value;
    }

    private static final IntFunction<String> fizzBuzzFunction = value -> {
        if (value <= 0 || value > 100) {
            return null;
        }
        String result = "";
        if (value % 3 == 0) {
            result += "Fizz";
        }
        if (value % 5 == 0) {
            result += "Buzz";
        }
        return result.isBlank() ? String.valueOf(value) : result;
    };

}
