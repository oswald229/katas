package com.kata.lift;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FloorTest {

    @Test
    void should_print_floor() {
        Floor floor = new Floor(0, 1);
        floor.addLift(new Lift("A"));

        String expected = "0     [A] 0";

        assertEquals(expected, floor.toString());
    }
}