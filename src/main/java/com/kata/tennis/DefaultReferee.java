package com.kata.tennis;

import java.util.Random;

public class DefaultReferee implements Referee {

    @Override
    public Exchange compute(TennisPlayer player1, TennisPlayer player2) {
        if (new Random().nextBoolean()) {
            return new DefaultExchange(player1, player2);
        }
        return new DefaultExchange(player2, player1);
    }
}
