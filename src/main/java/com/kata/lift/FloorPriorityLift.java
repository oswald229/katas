package com.kata.lift;

public class FloorPriorityLift extends SimpleLift {

    public FloorPriorityLift(Floors floors) {
        super(floors, new PlainTextLiftRenderer());
    }

    @Override
    public void addDestination(Floor floor) {
        if (floorIsOnLiftWay(floor)) {
            destinations.addFirst(floor);
            setOngoingDirection();
            return;
        }
        super.addDestination(floor);
    }

    boolean floorIsOnLiftWay(Floor floor) {
        return nextStop()
                .filter(nextFloor -> floor.to(nextFloor).equals(ongoingDirection()))
                .isPresent()

                && (liftIsAboveCurrentFloorUponDownshifting(floor) || liftIsBelowCurrentFloorUponUplifting(floor));
    }

    boolean liftIsBelowCurrentFloorUponUplifting(Floor floor) {
        return floor.level() < currentFloor().level() && ongoingDirection().equals(Direction.DOWN);
    }

    boolean liftIsAboveCurrentFloorUponDownshifting(Floor floor) {
        return floor.level() >= currentFloor().level() && ongoingDirection().equals(Direction.UP);
    }
}