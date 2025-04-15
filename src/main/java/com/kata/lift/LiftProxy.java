package com.kata.lift;

public class LiftProxy extends ConcreteLift {


    public LiftProxy(Floors floors) {
        this(floors, new PlainTextLiftRenderer());
    }

    public LiftProxy(Floors floors, LiftRenderer renderer) {
        super(floors, renderer);
    }

    @Override
    public void goToFloor(Floor floor) {
        if (floorIsOnLiftWay(floor)) {
            destinations.addFirst(floor);
            setOngoingDirection();
            return;
        }
        super.goToFloor(floor);
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