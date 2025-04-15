package com.kata.lift;

import java.util.Optional;

public interface Lift {
    Optional<Floor> nextStop();

    void addDestination(Floor floor);

    void move();

    void setOngoingDirection();

    int stops();

    Floor currentFloor();

    void open();

    @Override
    String toString();

    DoorState doorState();

    Direction ongoingDirection();
}
