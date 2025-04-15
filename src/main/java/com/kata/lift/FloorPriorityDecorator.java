package com.kata.lift;

public class FloorPriorityDecorator extends LiftDecorator {

    public FloorPriorityDecorator(Lift lift) {
        super(lift);
    }

    @Override
    public void addDestination(Floor floor) {
        if (floorIsOnLiftWay(floor)) {
            addPriorityDestination(floor);
            return;
        }
        super.addDestination(floor);
    }

    private void addPriorityDestination(Floor floor) {
        wrapped.destinations().addFirst(floor);
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