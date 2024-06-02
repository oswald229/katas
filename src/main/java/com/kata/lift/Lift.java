package com.kata.lift;

public class Lift {

    private final String id;
    private DoorState doorState;

    public Lift(String identifier) {
        id = identifier;
        doorState = DoorState.CLOSED;
    }

    @Override
    public String toString() {
        if (doorState.equals(DoorState.CLOSED)) {
            return "[%s]".formatted(id);
        }
        return "]%s[".formatted(id);

    }

    public void open() {
        doorState = DoorState.OPENED;
    }
}
