package com.kata.tennis;

record Winner(TennisPlayer player) {
    void output() {
        throw new GameWinnerException("%s Wins !".formatted(player.toString()));
    }
}
