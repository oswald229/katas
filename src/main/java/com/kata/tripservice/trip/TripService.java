package com.kata.tripservice.trip;

import com.kata.tripservice.exception.UserNotLoggedInException;
import com.kata.tripservice.user.User;
import com.kata.tripservice.user.UserSession;

import java.util.List;

public class TripService {
    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();
        return user.isFriendWith(loggedUser) ? getUserTrips(user) : List.of();
    }

    private User getLoggedUser() {
        User currentSessionUser = getCurrentSessionUser();
        if (currentSessionUser == null) {
            throw new UserNotLoggedInException();
        }
        return currentSessionUser;
    }

    protected User getCurrentSessionUser() {
        return UserSession.getInstance().getLoggedUser();
    }

    protected List<Trip> getUserTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
