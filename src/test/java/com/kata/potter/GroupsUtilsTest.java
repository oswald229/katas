package com.kata.potter;

import com.kata.permutations.Permutations;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.kata.potter.GroupsUtils.createGroups;
import static org.junit.jupiter.api.Assertions.*;

class GroupsUtilsTest {

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
        assertEquals(expected, createGroups(input, 2));
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
        assertEquals(expected, createGroups(input, 1));
    }

    @Test
    void should_create_potter_groups_ter() {
        List<PotterBooks> input = List.of(
                PotterBooks.ONE,
                PotterBooks.TWO,
                PotterBooks.THREE);

        List<Set<PotterBooks>> expected = List.of(
                Set.of(PotterBooks.ONE, PotterBooks.TWO, PotterBooks.THREE)
        );
        assertEquals(expected, createGroups(input, 3));
    }

    @Test
    void should_throw_when_more_items_per_group_than_items() {
        List<PotterBooks> input = List.of(
                PotterBooks.ONE,
                PotterBooks.TWO,
                PotterBooks.THREE);

        assertThrows(GroupsException.class, () -> createGroups(input, 4));
    }

}
