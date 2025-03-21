package com.kata.roman_numeral;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralTest {

    private final RomanNumeral sut = new RomanNumeral();

    @ParameterizedTest
    @CsvSource({
            "0, ''",
            "1, I",
            "2, II",
            "3, III",
            "4, IV",
            "5, V",
            "6, VI",
            "7, VII",
            "8, VIII",
            "9, IX",
            "10, X",
            "11, XI",
            "12, XII",
            "20, XX",
    })
    void should_return_roman_numeral(int i, String expected) {
        assertThat(sut.toRomanNumeral(i)).isEqualTo(expected);
    }

}
