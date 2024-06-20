package com.kata.tripservice.trip;

import com.kata.tripservice.exception.UserNotLoggedInException;
import com.kata.tripservice.user.User;
import com.kata.tripservice.user.UserSession;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class TripService {
    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        Optional<User> loggedUser = Optional.ofNullable(getLoggedUser());
        if (loggedUser.isPresent()){
            if (user.isFriend(loggedUser.get())) {
                return getUserTrips(user);
            }
            return List.of();
        }

        throw new UserNotLoggedInException();
    }

    @NotNull
    protected List<Trip> getUserTrips(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
