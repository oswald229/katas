package com.kata.lift;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LiftKataTest {
    /*
    a lift responds to calls containing a source floor and direction

    a lift has an attribute floor, which describes it’s current location

    a lift delivers passengers to requested floors

    you may implement current floor monitor
    you may implement direction arrows
    you may implement doors (opening and closing)
    you may implement DING!

    there can be more than one lift
     */

    static Floors myFloors = Floors.get(5);
    public static final Floor FLOOR_ZERO = myFloors.floor(0);
    public static final Floor FLOOR_ONE = myFloors.floor(1);
    public static final Floor FLOOR_TWO = myFloors.floor(2);
    public static final Floor FLOOR_THREE = myFloors.floor(3);
    public static final Floor FLOOR_FOUR = myFloors.floor(4);


    @Test
    void lift_should_go_to_destinations() {
        var lift = new Lift(myFloors);

        lift.goToFloor(FLOOR_ONE);
        lift.goToFloor(FLOOR_TWO);
        lift.goToFloor(FLOOR_THREE);


        assertThat(lift.currentFloor()).isEqualTo(FLOOR_ZERO);
        assertThat(lift.nextStop()).contains(FLOOR_ONE);
        lift.move();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR_ONE);
        assertThat(lift.nextStop()).contains(FLOOR_TWO);
        lift.move();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR_TWO);
        assertThat(lift.nextStop()).contains(FLOOR_THREE);
        lift.move();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR_THREE);
        assertThat(lift.nextStop()).isEmpty();
    }

    @Test
    void should_stop_if_floor_is_on_the_same_up_direction() {
        var lift = new Lift(myFloors);
        assertThat(lift.currentFloor()).isEqualTo(FLOOR_ZERO);

        lift.goToFloor(FLOOR_TWO);
        assertThat(lift.nextStop()).contains(FLOOR_TWO);

        lift.goToFloor(FLOOR_ONE);
        assertThat(lift.nextStop()).contains(FLOOR_ONE);

        assertThat(lift.stops()).isEqualTo(2);
    }


    @Test
    void should_stop_if_floor_is_on_the_same_up_direction_bis() {
        var lift = new Lift(myFloors);
        lift.goToFloor(FLOOR_TWO);
        lift.move();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR_TWO);

        lift.goToFloor(FLOOR_THREE);
        assertThat(lift.nextStop()).contains(FLOOR_THREE);

        lift.goToFloor(FLOOR_ONE);
        assertThat(lift.nextStop()).contains(FLOOR_THREE);

        lift.goToFloor(FLOOR_FOUR);
        assertThat(lift.nextStop()).contains(FLOOR_THREE);


    }

    @Test
    void should_stop_if_floor_is_on_the_same_down_direction() {
        var lift = new Lift(myFloors);

        lift.goToFloor(FLOOR_THREE);
        lift.move();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR_THREE);

        lift.goToFloor(FLOOR_ONE);
        assertThat(lift.nextStop()).contains(FLOOR_ONE);

        lift.goToFloor(FLOOR_TWO);
        assertThat(lift.nextStop()).contains(FLOOR_TWO);

        assertThat(lift.stops()).isEqualTo(2);
    }

    @Test
    void should_be_able_to_set_as_many_floors_as_desired() {
        int desiredFloorsAmount = 7;

        Floors floors = Floors.get(desiredFloorsAmount);

        assertThat(floors.levels()).isEqualTo(desiredFloorsAmount);
    }

    @Test
    void should_be_initialized_with_floors() {
        Floors floors = Floors.get(7);
        Lift lift = new Lift(floors);

        assertThat(lift.currentFloor())
                .isNotNull()
                .isEqualTo(floors.lowest());
    }

    record Floor(int level) implements Comparable<Floor> {
        public Direction to(Floor floor) {
            return this.level < floor.level ? Direction.UP : Direction.DOWN;
        }

        @Override
        public int compareTo(Floor o) {
            return Integer.compare(this.level, o.level);
        }
    }

    enum Direction {
        UP, DOWN, NONE
    }

    static class Lift {

        private Floor currentFloor;

        private final ArrayDeque<Floor> destinations = new ArrayDeque<>();
        private Direction ongoingDirection = Direction.NONE;

        public Lift(Floors floors) {
            this.currentFloor = floors.lowest();
        }

        public Optional<Floor> nextStop() {
            if (destinations.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(destinations.peekFirst());
        }

        public void goToFloor(Floor floor) {
            setOngoingDirection();
            if (isInSameDirectionToNextStop(floor)) {
                destinations.addFirst(floor);
                return;
            }
            destinations.add(floor);
        }

        private boolean isInSameDirectionToNextStop(Floor floor) {
            return nextStop()
                    .filter(nextFloor -> floor.to(nextFloor).equals(ongoingDirection))
                    .isPresent()

                    && (isAboveCurrentFloorUponDownshifting(floor) || isBelowCurrentFloorUponUplifting(floor));
        }

        private boolean isBelowCurrentFloorUponUplifting(Floor floor) {
            return floor.level < currentFloor().level && ongoingDirection.equals(Direction.DOWN);
        }

        private boolean isAboveCurrentFloorUponDownshifting(Floor floor) {
            return floor.level >= currentFloor().level && ongoingDirection.equals(Direction.UP);
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
    }

    private record Floors(Set<Floor> floors) {

        Floor floor(int level) {
            return floors.stream()
                    .filter(floor -> floor.level() == level)
                    .findFirst().get();
        }

        public static Floors get(int i) {
            Set<Floor> floors = IntStream.range(0, i)
                    .mapToObj(Floor::new)
                    .collect(Collectors.toSet());
            return new Floors(floors);
        }

        public int levels() {
            return floors.size();
        }

        public Floor lowest() {
            return floors.stream().min(Floor::compareTo).get();
        }
    }
}

















