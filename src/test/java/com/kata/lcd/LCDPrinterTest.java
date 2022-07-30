package com.kata.lcd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LCDPrinterTest {

    @ParameterizedTest
    @MethodSource("argumentsProvider")
    void should_return_number_as_lcd_digits(String number, String expected) {
        Assertions.assertEquals(expected, LCDPrinter.getLCDValue(number));
    }

    private static Stream<Arguments> argumentsProvider() {
        return Stream.of(
                Arguments.of("0", """
                        ._.
                        |.|
                        |_|
                        """.trim()),
                Arguments.of("9", """
                        ._.
                        |_|
                        ..|""".trim()),
                Arguments.of("910", """
                        ._. ... ._.
                        |_| ..| |.|
                        ..| ..| |_|
                        """.trim()),
                Arguments.arguments("0123456789", """
                        ._. ... ._. ._. ... ._. ._. ._. ._. ._.
                        |.| ..| ._| ._| |_| |_. |_. ..| |_| |_|
                        |_| ..| |_. ._| ..| ._| |_| ..| |_| ..|
                        """.trim())
        );
    }

}
