package com.kata.fizzbuzz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzTest {

    /**
     * - Write a program that prints the numbers from 1 to 100.
     * But for multiples of three print "Fizz"
     * instead of the number and for the multiples of five print "Buzz".
     * For numbers which are multiples of both
     * three and five print "FizzBuzz".
     */

    @Test
    void should_return_fizz_buzz() {

        List<String> expected = IntStream.range(1, 101)
                .mapToObj(value -> {

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
                }).toList();

        assertEquals(expected, FizzBuzz.getFizzBuzz());

    }


    @ParameterizedTest
    @CsvSource(value = {"5, Buzz", "3, Fizz", "15, FizzBuzz"})
    void apply_fizz_buzz_for_single_number(int n, String expected) {

        assertEquals(expected, FizzBuzz.applyFizzBuzz(n));

    }
    @ParameterizedTest
    @CsvSource(value = {"5, Buzz", "3, Fizz", "15, FizzBuzz", "200,", "-1,"})
    void apply_fizz_buzz_for_single_number_with_limit(int n, String expected) {
        assertEquals(expected, FizzBuzz.applyFizzBuzz(n));

    }

}
