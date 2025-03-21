package com.kata.roman_numeral;

import java.util.LinkedHashMap;
import java.util.stream.IntStream;

public class RomanNumeral {
    private static final LinkedHashMap<Integer, String> ARAB_ROMAN_MAPPING;

    static {
        ARAB_ROMAN_MAPPING = new LinkedHashMap<>();
        ARAB_ROMAN_MAPPING.put(10, "X");
        ARAB_ROMAN_MAPPING.put(9, "IX");
        ARAB_ROMAN_MAPPING.put(5, "V");
        ARAB_ROMAN_MAPPING.put(4, "IV");
        ARAB_ROMAN_MAPPING.put(1, "I");
    }

    public String toRomanNumeral(int i) {
        for (Integer key : ARAB_ROMAN_MAPPING.keySet()) {
            if (i >= key) {
                return ARAB_ROMAN_MAPPING.get(key) + toRomanNumeral(i - key);
            }
        }
        return IntStream.range(0, i)
                .mapToObj(value -> ARAB_ROMAN_MAPPING.get(1))
                .reduce(String::concat)
                .orElse("");
    }
}
