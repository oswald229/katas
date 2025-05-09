package com.kata.tripservice.user;

import com.kata.tripservice.trip.Trip;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Trip> trips = new ArrayList<>();
    private List<User> friends = new ArrayList<>();

    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> trips() {
        return trips;
    }

    public boolean isFriendWith(User user) {
        return friends.contains(user);
    }
}
