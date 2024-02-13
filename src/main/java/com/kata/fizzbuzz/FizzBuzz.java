package com.kata.fizzbuzz;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class FizzBuzz {

    private FizzBuzz() {
    }

    private static final IntFunction<String> fizzBuzzPredicate = value -> {
        String result = "";
        if (value % 3 == 0) {
            result += "Fizz";
        }
        if (value % 5 == 0) {
            result += "Buzz";
        }
        return result.isBlank() ? String.valueOf(value) : result;
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
