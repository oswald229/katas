package com.kata.fizzbuzz

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class KFizzBuzzTest {

    @ParameterizedTest
    @CsvSource(value = ["5, Buzz", "3, Fizz", "15, FizzBuzz"])
    fun apply_fizz_buzz_for_single_number(n: Int, expected: String) {
        assertEquals(expected, KFizzBuzz.applyFizzBuzz(n))
    }
}
