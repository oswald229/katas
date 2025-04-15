package com.kata.lift;

import java.util.ArrayDeque;
import java.util.Optional;

public class SimpleLift implements Lift {

    private final LiftRenderer renderer;
    protected final ArrayDeque<Floor> destinations = new ArrayDeque<>();
    private Floor currentFloor;
    private DoorState doorState = DoorState.CLOSED;
    private Direction ongoingDirection = Direction.NONE;

    public SimpleLift(Floors floors) {
        this.currentFloor = floors.lowest();
        this.renderer = new PlainTextLiftRenderer();
    }

    @Override
    public ArrayDeque<Floor> destinations() {
        return destinations;
    }

    @Override
    public Optional<Floor> nextStop() {
        if (destinations.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(destinations.peekFirst());
    }

    @Override
    public void addDestination(Floor floor) {
        destinations.add(floor);
        setOngoingDirection(); // TODO : Might get moved into the "move" method.
    }

    @Override
    public void move() {
        if (destinations.isEmpty()) {
            return;
        }
        currentFloor = destinations.pollFirst();
    }

    @Override
    public void setOngoingDirection() {
        ongoingDirection = nextStop()
                .map(nextStop -> currentFloor.to(nextStop))
                .orElse(Direction.NONE);
    }

    @Override
    public int stops() {
        return destinations.size();
    }

    @Override
    public Floor currentFloor() {
        return currentFloor;
    }

    @Override
    public void open() {
        doorState = DoorState.OPENED;
    }

    @Override
    public String toString() {
        return renderer.render(this);
    }


    @Override
    public DoorState doorState() {
        return doorState;
    }

    @Override
    public Direction ongoingDirection() {
        return ongoingDirection;
    }
}
