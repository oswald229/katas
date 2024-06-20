package com.kata.tripservice.trip;

import com.kata.tripservice.exception.CollaboratorCallException;
import com.kata.tripservice.user.User;

import java.util.List;

public class TripDAO {
    public static List<Trip> findTripsByUser(User user) {
        throw new CollaboratorCallException(
                "TripDAO should not be invoked on an unit test.");
    }
}
