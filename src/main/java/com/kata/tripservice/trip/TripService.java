package com.kata.tripservice.trip;

import com.kata.tripservice.exception.UserNotLoggedInException;
import com.kata.tripservice.user.User;
import com.kata.tripservice.user.UserSession;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TripService {
    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        } else if (user.friendsContains(loggedUser)) {
            return getUserTrips(user);
        }
        return List.of();
    }

    @NotNull
    protected List<Trip> getUserTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
