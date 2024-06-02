package com.kata.lift;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LiftManagerTest {

    @Test
    void should_print_state() {

        LiftManager liftManager = new LiftManager(4, 1);


        String expected = """
                3         3
                2         2
                1         1
                0     [A] 0""";

        assertEquals(expected, liftManager.getState());

    }
}
