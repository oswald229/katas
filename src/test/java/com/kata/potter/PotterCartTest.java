package com.kata.potter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PotterCartTest {

    @ParameterizedTest
    @MethodSource("should_tell_cart_price_arguments")
    void should_tell_price(List<Set<PotterBooks>> cartContent, double expectedPrice) {
        PotterCart cart = PotterCart.builder()
                .cartContent(cartContent)
                .build();

        assertEquals(expectedPrice, cart.getTotalPrice());
    }

    @Test
    void should_tell_total_items() {
        PotterCart cart = PotterCart.builder()
                .cartContent(List.of(Set.of(PotterBooks.ONE, PotterBooks.TWO)))
                .build();

        assertEquals(2, cart.totalItems());
    }

    @Test
    void should_tell_total_items_bis() {
        PotterCart cart = PotterCart.builder()
                .cartContent(List.of(Set.of(PotterBooks.ONE, PotterBooks.TWO), (Set.of(PotterBooks.ONE, PotterBooks.TWO))))
                .build();

        assertEquals(4, cart.totalItems());
    }

    private static Stream<Arguments> should_tell_cart_price_arguments() {
        return Stream.of(
                Arguments.of(List.of(Set.of(PotterBooks.ONE)), 8),
                Arguments.of(List.of(Set.of(PotterBooks.ONE, PotterBooks.TWO)), 16 * 0.95),
                Arguments.of(List.of(Set.of(PotterBooks.ONE, PotterBooks.TWO), Set.of(PotterBooks.ONE)), 16 * 0.95 + 8),
                Arguments.of(List.of(Set.of(PotterBooks.ONE, PotterBooks.TWO, PotterBooks.THREE)), 8 * 3 * 0.90),
                Arguments.of(List.of(Set.of(PotterBooks.ONE, PotterBooks.TWO, PotterBooks.THREE, PotterBooks.FOUR)), 8 * 4 * 0.80),
                Arguments.of(List.of(Set.of(PotterBooks.ONE, PotterBooks.TWO, PotterBooks.THREE, PotterBooks.FOUR, PotterBooks.FIVE)), 8 * 5 * 0.75),
                Arguments.of(List.of(
                        Set.of(PotterBooks.ONE, PotterBooks.TWO, PotterBooks.THREE, PotterBooks.FOUR, PotterBooks.FIVE),
                        Set.of(PotterBooks.ONE, PotterBooks.TWO)), 8 * 5 * 0.75 + (16 * 0.95))
        );
    }
}
