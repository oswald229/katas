package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

class Game {

    private final LinkedList<Exchange> exchanges;

    Game(LinkedList<Exchange> exchanges) {
        this.exchanges = exchanges;
    }

    public Optional<TennisPlayer> winner() {
        return Optional.ofNullable(exchanges.peekLast())
                .filter(Exchange::wasGameWinning)
                .map(Exchange::winner);
    }

    public void add(Exchange playedExchange) {
        exchanges.add(playedExchange);
    }
}
