package com.kata.tripservice.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void should_return_true_when_user_is_friend() {
        User user = new User();
        User friend = new User();
        user.addFriend(friend);

        assertThat(user.friendsContains(friend)).isTrue();
    }
}