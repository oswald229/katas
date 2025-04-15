package com.kata.lift;

import java.util.ArrayDeque;
import java.util.Optional;

public class LiftDecorator implements Lift {

    protected final Lift wrapped;

    public LiftDecorator(Lift lift) {
        wrapped = lift;
    }

    @Override
    public ArrayDeque<Floor> destinations() {
        return wrapped.destinations();
    }

    @Override
    public Optional<Floor> nextStop() {
        return wrapped.nextStop();
    }

    @Override
    public void addDestination(Floor floor) {
        wrapped.addDestination(floor);
    }

    @Override
    public void move() {
        wrapped.move();
    }

    @Override
    public void setNextDirection() {
        wrapped.setNextDirection();
    }

    @Override
    public int stops() {
        return wrapped.stops();
    }

    @Override
    public Floor currentFloor() {
        return wrapped.currentFloor();
    }

    @Override
    public void open() {
        wrapped.open();
    }

    @Override
    public DoorState doorState() {
        return wrapped.doorState();
    }

    @Override
    public Direction ongoingDirection() {
        return wrapped.ongoingDirection();
    }
}
