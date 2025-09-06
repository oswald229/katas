package com.kata.roman_numeral;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralTest {

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
        assertThat(new RomanNumeral().toRomanNumeral(i)).isEqualTo(expected);
    }
    //@Disabled
    @ParameterizedTest
    @CsvSource({
            "I, 1",
            "II, 2",
            "III, 3",
            "IV, 4",
            "V, 5",
            "VI, 6",
            "VII, 7",
            "VIII, 8",
            "X, 10",
            "XII, 12",
            "XIII, 13",
            "XIV, 14",
            "XVII, 17",
            "XIX, 19",
            "XX, 20",
    })
    void should_return_arabic_numeral(String roman, int expected) {
        assertThat(new RomanNumeral().toArabic(roman)).isEqualTo(expected);
    }

}
