package com.kata.lift;

public class LiftManager {

     boolean floorIsOnLiftWay(Floor floor, Lift lift) {
        return lift.nextStop()
                .filter(nextFloor -> floor.to(nextFloor).equals(lift.ongoingDirection()))
                .isPresent()

                && (liftIsAboveCurrentFloorUponDownshifting(floor, lift) || liftIsBelowCurrentFloorUponUplifting(floor, lift));
    }

    static boolean liftIsBelowCurrentFloorUponUplifting(Floor floor, Lift lift) {
        return floor.level() < lift.currentFloor().level() && lift.ongoingDirection().equals(Direction.DOWN);
    }

    static boolean liftIsAboveCurrentFloorUponDownshifting(Floor floor, Lift lift) {
        return floor.level() >= lift.currentFloor().level() && lift.ongoingDirection().equals(Direction.UP);
    }
}