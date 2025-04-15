package com.kata.lift;

import java.util.ArrayDeque;
import java.util.Optional;

public interface Lift {
    ArrayDeque<Floor> destinations();

    Optional<Floor> nextStop();

    void addDestination(Floor floor);

    void move();

    void setNextDirection();

    int stops();

    Floor currentFloor();

    void open();

    @Override
    String toString();

    DoorState doorState();

    Direction ongoingDirection();
}
