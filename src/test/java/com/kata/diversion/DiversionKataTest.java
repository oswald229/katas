package com.kata.diversion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiversionKataTest {

    private final BinaryDiversion binaryDiversion = new BinaryDiversion();


    /**
     * Think of binary numbers: sequences of 0's and 1's. How many
     * n-digit binary numbers are there that don't have two adjacent
     * 1 bits?
     *
     * For example, for three-digit numbers, Five of the possible
     * eight combinations meet the criteria:
     *
     *    000, 001, 010, 011, 100, 101, 110, 111.
     *
     * What is the number for sequences of length 4, 5, 10, n?
     *
     * Having worked out the pattern, there's a second part to the
     * question: can you prove why that relationship exists?
     */

    @Test
    void should_return_binaries_with_given_nb_of_digits() {

        int n = 1;

        List<String> expected = List.of("0", "1");

        assertEquals(expected, binaryDiversion.getBinaries(n));

    }
    @Test
    void should_return_binaries_with_given_nb_of_digits_bis() {

        int n = 2;

        List<String> expected = List.of("00", "01", "10", "11");

        assertEquals(expected, binaryDiversion.getBinaries(n));

    }

    @Test
    void should_return_binaries_with_given_nb_of_digits_ter() {

        int n = 3;
        List<String> expected = List.of(
                "000",
                "001",
                "010",
                "011",
                "100",
                "101",
                "110",
                "111");

        assertEquals(expected, binaryDiversion.getBinaries(n));

    }

    @Test
    void should_tell_if_has_two_adjacent_1_bits() {
        String s = "101";

        assertFalse(binaryDiversion.hasTwoAdjacent1Bits(s));
    }

    @Test
    void should_tell_if_has_two_adjacent_1_bits_bis() {
        String s = "111";

        assertTrue(binaryDiversion.hasTwoAdjacent1Bits(s));
    }

    @Test
    void should_tell_if_has_two_adjacent_1_bits_ter() {
        String s = "100110001";

        assertTrue(binaryDiversion.hasTwoAdjacent1Bits(s));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3",
            "3, 5",
            "4, 8"
    })
    void should_tell_amount_of_item_matching_criteria(int n, int expected) {
        assertEquals(expected, binaryDiversion.getDiversionCriteria(n));
    }
}
