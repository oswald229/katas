package com.kata.roman_numeral;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RomanNumeral {
    private final LinkedHashMap<Integer, String> ARAB_ROMAN_MAPPING;
    private final Map<String, Integer> ROMAN_ARAB ;


    public RomanNumeral() {
        ARAB_ROMAN_MAPPING = new LinkedHashMap<>();
        ARAB_ROMAN_MAPPING.put(10, "X");
        ARAB_ROMAN_MAPPING.put(9, "IX");
        ARAB_ROMAN_MAPPING.put(5, "V");
        ARAB_ROMAN_MAPPING.put(4, "IV");
        ARAB_ROMAN_MAPPING.put(1, "I");

        ROMAN_ARAB = Map.of(
                "I", 1,
                "IV", 4,
                "V", 5,
                "IX", 9,
                "X", 10
        );
    }

    public String toRomanNumeral(int i) {
        for (Map.Entry<Integer, String> arabRomanEntry : ARAB_ROMAN_MAPPING.entrySet()) {
            if (i >= arabRomanEntry.getKey()) {
                return arabRomanEntry.getValue() + toRomanNumeral(i - arabRomanEntry.getKey());
            }
        }
        return IntStream.range(0, i)
                .mapToObj(value -> ARAB_ROMAN_MAPPING.get(1))
                .reduce(String::concat)
                .orElse("");
    }

    public int toArabic(String roman) {
        for (Map.Entry<String, Integer> romanArabEntry : ROMAN_ARAB.entrySet()) {
            String romanKey = romanArabEntry.getKey();
            if (roman.startsWith(romanKey)) {
                return romanArabEntry.getValue() + toArabic(roman.substring(romanKey.length()));
            }
        }
        return 0;
    }
}
