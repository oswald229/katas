package com.kata.potter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.kata.potter.PotterUtils.createPotterGroups;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PotterUtilsTest {

    @Test
    void should_create_potter_groups() {
        List<PotterBooks> input = List.of(
                PotterBooks.ONE,
                PotterBooks.TWO,
                PotterBooks.THREE);

        List<Set<PotterBooks>> expected = List.of(
                Set.of(PotterBooks.ONE, PotterBooks.TWO),
                Set.of(PotterBooks.THREE)
        );
        assertEquals(expected, createPotterGroups(2, input));
    }

    @Test
    void should_create_potter_groups_bis() {
        List<PotterBooks> input = List.of(
                PotterBooks.ONE,
                PotterBooks.TWO,
                PotterBooks.THREE);

        List<Set<PotterBooks>> expected = List.of(
                Set.of(PotterBooks.ONE),
                Set.of(PotterBooks.TWO),
                Set.of(PotterBooks.THREE)
        );
        assertEquals(expected, createPotterGroups(1, input));
    }

    @Test
    void should_generate_possible_permutations() {
        List<PotterBooks> input = List.of(
                PotterBooks.ONE,
                PotterBooks.TWO);

        List<List<PotterBooks>> expected = List.of(
                List.of(PotterBooks.ONE, PotterBooks.TWO),
                List.of(PotterBooks.TWO, PotterBooks.ONE)
        );

        List<List<PotterBooks>> result = PotterUtils.possiblePermutations(input);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    void should_generate_possible_permutations_bis() {
        List<PotterBooks> input = List.of(
                PotterBooks.ONE,
                PotterBooks.TWO,
                PotterBooks.THREE);

        List<List<PotterBooks>> expected = List.of(
                List.of(PotterBooks.ONE, PotterBooks.TWO, PotterBooks.THREE),
                List.of(PotterBooks.ONE, PotterBooks.THREE, PotterBooks.TWO),
                List.of(PotterBooks.TWO, PotterBooks.ONE, PotterBooks.THREE),
                List.of(PotterBooks.TWO, PotterBooks.THREE, PotterBooks.ONE),
                List.of(PotterBooks.THREE, PotterBooks.ONE, PotterBooks.TWO),
                List.of(PotterBooks.THREE, PotterBooks.TWO, PotterBooks.ONE)
        );

        assertTrue(PotterUtils.possiblePermutations(input).containsAll(expected));
    }
}
