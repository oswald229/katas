package com.kata.lift;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

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
        var lift = getLift();

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
        var lift = getLift();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR_ZERO);

        lift.goToFloor(FLOOR_TWO);
        assertThat(lift.nextStop()).contains(FLOOR_TWO);

        lift.goToFloor(FLOOR_ONE);
        assertThat(lift.nextStop()).contains(FLOOR_ONE);

        assertThat(lift.stops()).isEqualTo(2);
    }


    @Test
    void should_stop_if_floor_is_on_the_same_up_direction_bis() {
        var lift = getLift();
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
        var lift = getLift();

        lift.goToFloor(FLOOR_THREE);
        lift.move();
        assertThat(lift.currentFloor()).isEqualTo(FLOOR_THREE);

        lift.goToFloor(FLOOR_ONE);
        assertThat(lift.nextStop()).contains(FLOOR_ONE);

        lift.goToFloor(FLOOR_TWO);
        assertThat(lift.nextStop()).contains(FLOOR_TWO);

        assertThat(lift.stops()).isEqualTo(2);
    }

    @NotNull
    private static Lift getLift() {
        return new LiftFloorPriorityDecorator(myFloors);
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
        Lift lift = new ConcreteLift(floors);

        assertThat(lift.currentFloor())
                .isNotNull()
                .isEqualTo(floors.lowest());
    }

}

















