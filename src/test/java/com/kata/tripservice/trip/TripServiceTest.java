package com.kata.tripservice.trip;

import com.kata.tripservice.exception.UserNotLoggedInException;
import com.kata.tripservice.user.User;
import org.assertj.core.api.ThrowableAssert;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripServiceTest {
    @Test
    void should_throw_user_not_logged_in_exception() {
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedUser() {
                return null;
            }
        };
        User user = new User();

        ThrowableAssert.ThrowingCallable getTrips = () -> tripService.getTripsByUser(user);

        assertThatThrownBy(getTrips)
                .isInstanceOf(UserNotLoggedInException.class);
    }

    @Test
    void should_return_empty_list_when_user_has_no_friends() {
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedUser() {
                return new User();
            }
        };
        User user = new User();

        assertThat(tripService.getTripsByUser(user))
                .isEmpty();

    }

    @Test
    void should_return_empty_list_when_user_is_not_a_friend() {
        User loggedUser = new User();
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }
        };
        User user = new User();
        user.addFriend(new User());

        assertThat(tripService.getTripsByUser(user))
                .isEmpty();
    }

    @Test
    void should_return_trip_list_when_user_is_a_friend() {
        User loggedUser = new User();
        User user = new User();
        user.addFriend(loggedUser);

        Trip trip = new Trip();
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }

            @NotNull
            @Override
            protected List<Trip> getUserTrips(User user) {
                user.addTrip(trip);
                return user.trips();
            }
        };

        assertThat(tripService.getTripsByUser(user))
                .containsOnly(trip);

    }
}