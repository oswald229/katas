package com.kata.tripservice.user;

import com.kata.tripservice.exception.CollaboratorCallException;

public class UserSession {
    private static final UserSession userSession = new UserSession();

    private UserSession() {
    }

    public static UserSession getInstance() {
        return userSession;
    }

    public User getLoggedUser() {
        throw new CollaboratorCallException(
                "UserSession.getLoggedUser() should not be called in an unit test");
    }
}
