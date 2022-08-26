package com.kata.karatechop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KarateChopTest {
    /*
    Write a binary chop method that takes an integer search target and a sorted array of integers.
    It should return the integer index of the target in the array, or -1 if the target is not in the array.

    The signature will logically be: chop(int, array_of_int)  -> int

     */

    @ParameterizedTest
    @MethodSource("should_tell_when_value_not_in_list_arguments")
    void should_tell_when_value_not_in_list(int i, List<Integer> input, int expected) {
        assertEquals(expected, KarateChop.chop(i, input));
    }

    private static Stream<Arguments> should_tell_when_value_not_in_list_arguments() {
        return Stream.of(
                Arguments.of(4, List.of(1, 2, 3), -1),
                Arguments.of(10, List.of(1, 2, 3), -1),
                Arguments.of(10, List.of(), -1)
        );
    }

    @ParameterizedTest
    @MethodSource("should_return_index_of_value_arguments")
    void should_return_index_of_value(int i, List<Integer> input, int expected) {
        assertEquals(expected, KarateChop.chop(i, input));
    }

    private static Stream<Arguments> should_return_index_of_value_arguments() {
        return Stream.of(
                Arguments.of(3, List.of(1, 2, 3, 4, 10), 2),
                Arguments.of(10, List.of(1, 2, 3, 4, 10, 12), 4),
                Arguments.of(7, IntStream.range(1, 10).boxed().toList(), 6),
                Arguments.of(11, IntStream.range(1, 13).boxed().toList(), 10),
                Arguments.of(10, IntStream.range(1, 13).boxed().toList(), 9),
                Arguments.of(99, IntStream.range(1, 101).boxed().toList(), 98),
                Arguments.of(2, IntStream.range(1, 14).boxed().toList(), 1),
                Arguments.of(5, IntStream.range(1, 14).boxed().toList(), 4)
        );
    }

    @Test
    void test_chop() {

        assertEquals(-1, KarateChop.chop(3, List.of()));
        assertEquals(-1, KarateChop.chop(3, List.of(1)));
        assertEquals(0, KarateChop.chop(1, List.of(1)));
        assertEquals(0, KarateChop.chop(1, List.of(1, 3, 5)));
        assertEquals(1, KarateChop.chop(3, List.of(1, 3, 5)));
        assertEquals(2, KarateChop.chop(5, List.of(1, 3, 5)));
        assertEquals(-1, KarateChop.chop(0, List.of(1, 3, 5)));
        assertEquals(-1, KarateChop.chop(2, List.of(1, 3, 5)));
        assertEquals(-1, KarateChop.chop(4, List.of(1, 3, 5)));
        assertEquals(-1, KarateChop.chop(6, List.of(1, 3, 5)));
        assertEquals(0, KarateChop.chop(1, List.of(1, 3, 5, 7)));
        assertEquals(1, KarateChop.chop(3, List.of(1, 3, 5, 7)));
        assertEquals(2, KarateChop.chop(5, List.of(1, 3, 5, 7)));
        assertEquals(3, KarateChop.chop(7, List.of(1, 3, 5, 7)));
        assertEquals(-1, KarateChop.chop(0, List.of(1, 3, 5, 7)));
        assertEquals(-1, KarateChop.chop(2, List.of(1, 3, 5, 7)));
        assertEquals(-1, KarateChop.chop(4, List.of(1, 3, 5, 7)));
        assertEquals(-1, KarateChop.chop(6, List.of(1, 3, 5, 7)));
        assertEquals(-1, KarateChop.chop(8, List.of(1, 3, 5, 7)));
    }

}
