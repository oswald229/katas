package com.kata.lcd;

import java.util.StringJoiner;
import java.util.function.UnaryOperator;

public class LCDPrinter {

    private LCDPrinter() {
    }

    public static String getLCDValue(String number) {
        return buildLcd(number);
    }

    private static String buildLcd(String number) {
        char[] numberChars = number.toCharArray();
        return new StringJoiner("\n")
                .add(buildLCDPart(numberChars, LCDPrinter::buildTop))
                .add(buildLCDPart(numberChars, LCDPrinter::buildMiddle))
                .add(buildLCDPart(numberChars, LCDPrinter::buildEnd))
                .toString();
    }

    private static String buildLCDPart(char[] numberChars, UnaryOperator<String> builder) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (char charItem : numberChars) {
            stringJoiner.add(builder.apply(String.valueOf(charItem)));
        }
        return stringJoiner.toString();
    }


    private static String buildTop(String number) {
        return switch (Integer.parseInt(number)) {
            case 0, 2, 3, 5, 6, 7, 8, 9 -> "._.";
            default -> "...";
        };
    }

    private static String buildMiddle(String number) {
        return switch (Integer.parseInt(number)) {
            case 0 -> "|.|";
            case 1, 7 -> "..|";
            case 2, 3 -> "._|";
            case 4, 8, 9 -> "|_|";
            default -> "|_.";
        };
    }

    private static String buildEnd(String number) {
        return switch (Integer.parseInt(number)) {
            case 0, 6, 8 -> "|_|";
            case 1, 4, 7, 9 -> "..|";
            case 2 -> "|_.";
            default -> "._|";
        };
    }
}
