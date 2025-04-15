package com.kata.lift;

import java.util.ArrayDeque;
import java.util.Optional;

public class ConcreteLift {

    private final LiftRenderer renderer;
    private Floor currentFloor;
    private DoorState doorState = DoorState.CLOSED;

    protected final ArrayDeque<Floor> destinations = new ArrayDeque<>();
    private Direction ongoingDirection = Direction.NONE;

    public ConcreteLift(Floors floors, LiftRenderer renderer) {
        this.currentFloor = floors.lowest();
        this.renderer = renderer;
    }

    public Optional<Floor> nextStop() {
        if (destinations.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(destinations.peekFirst());
    }

    public void goToFloor(Floor floor) {
        destinations.add(floor);
        setOngoingDirection();
    }

    public void move() {
        if (destinations.isEmpty()) {
            return;
        }
        currentFloor = destinations.pollFirst();
    }

    protected void setOngoingDirection() {
        ongoingDirection = nextStop()
                .map(nextStop -> currentFloor.to(nextStop))
                .orElse(Direction.NONE);
    }

    public int stops() {
        return destinations.size();
    }

    public Floor currentFloor() {
        return currentFloor;
    }

    public void open() {
        doorState = DoorState.OPENED;
    }

    @Override
    public String toString() {
        return renderer.render(this);
    }


    public DoorState doorState() {
        return doorState;
    }

    public Direction ongoingDirection() {
        return ongoingDirection;
    }
}
