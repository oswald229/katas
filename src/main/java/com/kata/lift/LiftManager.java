package com.kata.lift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringJoiner;

public class LiftManager {

    private final ArrayList<Floor> floors;

    public LiftManager(int floorsAmount, int liftAmount) {
        this.floors = new ArrayList<>(floorsAmount);

        for (int i = 0; i < floorsAmount; i++) {
            floors.add(new Floor(i, liftAmount));
        }

        Floor firstFloor = floors.get(0);
        for (int i = 0; i < liftAmount; i++) {
            firstFloor.addLift(new Lift("A"));
        }

    }

    public String getState() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        Collections.reverse(floors);
        for (Floor floor : floors) {
            stringJoiner.add(floor.toString());
        }
        Collections.reverse(floors);
        return stringJoiner.toString();
    }
}
