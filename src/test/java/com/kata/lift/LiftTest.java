package com.kata.lift;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LiftTest {

    @ParameterizedTest
    @CsvSource({
            "[], false",
            "[], false",
            "] [, true"})
    void should_be_printed_according_to_its_state(String expected, boolean openDoor) {
        Lift lift = new Lift(Floors.get(1));

        if (openDoor){
            lift.open();
        }
        assertEquals(expected, lift.toString());
    }
}
