package com.kata.lift;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Optional;

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


    @Test
    void lift_should_go_to_destinations() {
        var lift = new Lift();

        lift.goToFloor(FLOOR.ONE);
        lift.goToFloor(FLOOR.TWO);
        lift.goToFloor(FLOOR.THREE);


        assertThat(lift.currentFloor()).isEqualTo(FLOOR.ZERO);
        assertThat(lift.nextStop()).contains(FLOOR.ONE);
        lift.move();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR.ONE);
        assertThat(lift.nextStop()).contains(FLOOR.TWO);
        lift.move();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR.TWO);
        assertThat(lift.nextStop()).contains(FLOOR.THREE);
        lift.move();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR.THREE);
        assertThat(lift.nextStop()).isEmpty();
    }

    @Test
    void should_stop_if_floor_is_on_the_same_up_direction() {
        var lift = new Lift();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR.ZERO);

        lift.goToFloor(FLOOR.TWO);
        assertThat(lift.nextStop()).contains(FLOOR.TWO);

        lift.goToFloor(FLOOR.ONE);
        assertThat(lift.nextStop()).contains(FLOOR.ONE);

        assertThat(lift.stops()).isEqualTo(2);
    }

    @Test
    void should_stop_if_floor_is_on_the_same_down_direction() {
        var lift = new Lift();

        lift.goToFloor(FLOOR.THREE);
        lift.move();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR.THREE);

        lift.goToFloor(FLOOR.ONE);
        assertThat(lift.nextStop()).contains(FLOOR.ONE);

        lift.goToFloor(FLOOR.TWO);
        assertThat(lift.nextStop()).contains(FLOOR.TWO);

        assertThat(lift.stops()).isEqualTo(2);
    }


    enum FLOOR {
        ZERO(0), ONE(1), TWO(2), THREE(3);

        private final int level;

        FLOOR(int level) {
            this.level = level;
        }

        public Direction to(FLOOR floor) {
            return this.level < floor.level ? Direction.UP : Direction.DOWN;
        }
    }

    enum Direction {
        UP, DOWN, NONE
    }

    static class Lift {

        private FLOOR currentFloor = FLOOR.ZERO;

        private final ArrayDeque<FLOOR> destinations = new ArrayDeque<>();
        private Direction ongoingDirection = Direction.NONE;


        public Optional<FLOOR> nextStop() {
            if (destinations.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(destinations.peekFirst());
        }

        public void goToFloor(FLOOR floor) {
            setOngoingDirection();
            if (isInSameDirectionToNextStop(floor)) {
                destinations.addFirst(floor);
                return;
            }
            destinations.add(floor);
        }

        private boolean isInSameDirectionToNextStop(FLOOR floor) {
            return nextStop()
                    .filter(nextFloor -> floor.to(nextFloor).equals(ongoingDirection))
                    .isPresent();
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

        public FLOOR currentFloor() {
            return currentFloor;
        }

        public int stops() {
            return destinations.size();
        }
    }

}

















