package com.kata.lift;

import java.util.ArrayDeque;
import java.util.Optional;

public class Lift {

    private final LiftRenderer renderer;
    private final LiftManager liftManager;
    private Floor currentFloor;
    private DoorState doorState = DoorState.CLOSED;

    private final ArrayDeque<Floor> destinations = new ArrayDeque<>();
    private Direction ongoingDirection = Direction.NONE;

    public Lift(Floors floors) {
        this.currentFloor = floors.lowest();
        renderer = new PlainTextLiftRenderer();
        liftManager = new LiftManager();
    }

    public Optional<Floor> nextStop() {
        if (destinations.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(destinations.peekFirst());
    }

    public void goToFloor(Floor floor) {
        if (floorIsOnMyWay(floor)) {
            destinations.addFirst(floor);
        } else {
            destinations.add(floor);
        }
        setOngoingDirection();
    }

    private boolean floorIsOnMyWay(Floor floor) {
        return liftManager.liftNextStopIsInSameDirectionTo(floor, this);
    }

    public void move() {
        if (destinations.isEmpty()) {
            return;
        }
        currentFloor = destinations.pollFirst();
    }

    private void setOngoingDirection() {
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
