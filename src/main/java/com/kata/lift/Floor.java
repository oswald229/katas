package com.kata.lift;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Floor {

    private final int liftAmount;
    private int floorIndex;

    private ArrayList<Lift> lifts;

    public Floor(int index, int liftAmount) {
        lifts = new ArrayList<>();
        floorIndex = index;
        this.liftAmount = liftAmount;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        // Index column
        stringBuilder.append(floorIndex);

        // Caller column
        stringBuilder.append("   ");
        stringBuilder.append("  ");


        StringJoiner stringJoiner = new StringJoiner("  ");
        if (lifts.isEmpty()) {
            for (int i = 0; i < liftAmount; i++) {
                stringJoiner.add("   ");
            }

        } else {

            for (Lift lift : lifts) {
                stringJoiner.add(lift.toString());
            }
        }
        stringBuilder.append(stringJoiner);

        stringBuilder.append(" ").append(floorIndex);

        return stringBuilder.toString();
    }

    public void addLift(Lift lift) {
        lifts.add(lift);
    }
}
