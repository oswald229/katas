package com.kata.lift;

public class PlainTextLiftRenderer implements LiftRenderer {

    @Override
    public String render(ConcreteLift lift) {
        return lift.doorState().equals(DoorState.CLOSED) ? "[]" : "] [";
    }
}
