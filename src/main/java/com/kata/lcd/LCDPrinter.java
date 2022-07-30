package com.kata.lcd;

import java.util.StringJoiner;

public class LCDPrinter {

    private LCDPrinter() {
    }

    public static String getLCDValue(String number) {
        return buildLcd(number);
    }

    private static String buildLcd(String number) {
        StringJoiner resultJoiner = new StringJoiner("\n");
        char[] numberChars = number.toCharArray();
        resultJoiner.add(buildTop(numberChars));
        resultJoiner.add(buildMiddle(numberChars));
        resultJoiner.add(buildEnd(numberChars));
        return resultJoiner.toString();
    }

    private static String buildEnd(char[] numberChars) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (char charItem : numberChars) {
            stringJoiner.add(buildEnd(String.valueOf(charItem)));
        }
        return stringJoiner.toString();
    }

    private static String buildMiddle(char[] numberChars) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (char charItem : numberChars) {
            stringJoiner.add(buildMiddle(String.valueOf(charItem)));
        }
        return stringJoiner.toString();
    }

    private static String buildTop(char[] numberChars) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (char charItem : numberChars) {
            stringJoiner.add(buildTop(String.valueOf(charItem)));
        }
        return stringJoiner.toString();
    }

    private static String buildTop(String number) {

        return switch (Integer.parseInt(number)) {
            case 0, 2, 3, 5, 6, 7, 8, 9 -> "._.";
            case 1, 4 -> "...";
            default -> "";
        };
    }

    private static String buildMiddle(String number) {
        return switch (Integer.parseInt(number)) {
            case 0 -> "|.|";
            case 1, 7 -> "..|";
            case 2, 3 -> "._|";
            case 4, 8, 9 -> "|_|";
            case 5, 6 -> "|_.";
            default -> "";
        };

    }

    private static String buildEnd(String number) {
        return switch (Integer.parseInt(number)) {
            case 0, 6, 8 -> "|_|";
            case 1, 4, 7, 9 -> "..|";
            case 2 -> "|_.";
            case 3, 5 -> "._|";
            default -> "";
        };

    }
}
