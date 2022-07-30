package com.kata.fizzbuzz;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class FizzBuzz {

    private FizzBuzz() {
    }

    private static final IntFunction<String> fizzBuzzPredicate = value -> {

        if (value % 3 == 0 && value % 5 == 0) {
            return "FizzBuzz";
        }
        if (value % 3 == 0) {
            return "Fizz";
        }
        if (value % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(value);
    };

    public static List<String> getFizzBuzz() {
        return IntStream.range(1, 101)
                .mapToObj(fizzBuzzPredicate).toList();
    }

    public static String applyFizzBuzz(int n) {
        if (n > 0 && n < 101) {
            return fizzBuzzPredicate.apply(n);
        }
        return null;
    }
}
