package com.kata.permutations;

import com.kata.potter.PotterBooks;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PermutationsTest {


    @Test
    void should_tell_amount_of_possible_permutations() {

        List<Long> inputs = LongStream.rangeClosed(0, 9).boxed().toList();
        long items = 4;
        long result = Permutations.count(inputs, items);

        assertEquals(5040, result);
    }

    @Test
    void should_tell_amount_of_possible_permutations_bis() {

        List<Long> inputs = LongStream.rangeClosed(1, 3).boxed().toList();
        long items = 3;
        long result = Permutations.count(inputs, items);

        assertEquals(6, result);
    }


    @Test
    void should_return_possible_permutations() {
        List<Integer> list = List.of(1, 2);

        List<List<Integer>> expected = List.of(
                List.of(1, 2), List.of(2, 1)
        );
        assertEquals(expected, Permutations.getPermutations(list));
    }

    @Test
    void should_return_possible_permutations_bis() {
        List<Integer> list = List.of(1, 2, 3);

        List<List<Integer>> expected = List.of(
                List.of(1, 2, 3),
                List.of(1, 3, 2),
                List.of(2, 1, 3),
                List.of(2, 3, 1),
                List.of(3, 1, 2),
                List.of(3, 2, 1)
        );
        assertEquals(expected, Permutations.getPermutations(list));
    }

    @Test
    void should_return_possible_permutations_four_bis() {
        List<Integer> list = List.of(1, 2, 3, 4);

        List<List<Integer>> expected = List.of(
                List.of(1, 2, 3, 4),
                List.of(2, 1, 3, 4),
                List.of(3, 1, 2, 4),
                List.of(1, 3, 2, 4),
                List.of(2, 3, 1, 4),
                List.of(3, 2, 1, 4),
                List.of(3, 2, 4, 1),
                List.of(2, 3, 4, 1),
                List.of(4, 3, 2, 1),
                List.of(3, 4, 2, 1),
                List.of(2, 4, 3, 1),
                List.of(4, 2, 3, 1),
                List.of(4, 1, 3, 2),
                List.of(1, 4, 3, 2),
                List.of(3, 4, 1, 2),
                List.of(4, 3, 1, 2),
                List.of(1, 3, 4, 2),
                List.of(3, 1, 4, 2),
                List.of(2, 1, 4, 3),
                List.of(1, 2, 4, 3),
                List.of(4, 2, 1, 3),
                List.of(2, 4, 1, 3),
                List.of(1, 4, 2, 3),
                List.of(4, 1, 2, 3)
        );
        List<List<Integer>> actual = Permutations.getPermutations(list);
        assertEquals(Permutations.count(list), actual.size());
        assertTrue(actual.containsAll(expected));
    }

    //40320
    @Test
    void should_return_possible_permutations_b() {
        List<Integer> list = IntStream.range(1, 4).boxed().toList();
        List<List<Integer>> result = Permutations.getPermutations(list);
        System.out.println(result);
        assertEquals(6, result.size());
    }

    @Test
    void should_return_possible_permutations_four() {
        List<Integer> list = IntStream.range(1, 5).boxed().toList();
        assertEquals(24, Permutations.getPermutations(list).size());
    }

    @Test
    void should_return_possible_permutations_five() {
        List<Integer> list = IntStream.range(1, 6).boxed().toList();
        assertEquals(24 * 5, Permutations.getPermutations(list).size());
    }


    @Test
    void should_return_possible_permutations_ter() {
        List<Integer> list = IntStream.range(1, 9).boxed().toList();
        assertEquals(40320, Permutations.getPermutations(list).size());
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

        List<List<PotterBooks>> result = Permutations.getPermutations(input);
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

        assertTrue(Permutations.getPermutations(input).containsAll(expected));
    }
}
