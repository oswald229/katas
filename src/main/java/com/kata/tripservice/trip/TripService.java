package com.kata.tripservice.trip;

import com.kata.tripservice.exception.UserNotLoggedInException;
import com.kata.tripservice.user.User;
import com.kata.tripservice.user.UserSession;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TripService {
    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        User loggedUser = getLoggedUser();
        boolean isFriend = false;
        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend.equals(loggedUser)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                tripList = getUserTrips(user);
            }
            return tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    @NotNull
    protected List<Trip> getUserTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
