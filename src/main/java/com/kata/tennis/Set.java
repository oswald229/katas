package com.kata.tennis;

import java.util.LinkedList;
import java.util.Optional;

class Set {

    private final LinkedList<Game> games;

    Set() {
        this.games = new LinkedList<>();
    }

    public void addGame(Game game) {
        this.games.add(game);
        updateScore();
    }

    public Optional<TennisPlayer> winner() {
        Game lastGame = lastGame();
        if (lastGame.wasSetWinning()) {
            return Optional.of(lastGame.winner());
        }
        return Optional.empty();
    }

    private Game lastGame() {
        return this.games.peekLast();
    }


    private void updateScore() {
        Game lastGame = lastGame();
        if (lastGame.wasDeuces()) {
            lastGame.winner().advantage();
            return;
        }
        if (lastGame.wasLostAdvantage()) {
            lastGame.looser().decreaseScore();
            return;
        }
        lastGame.winner().increaseScore();
    }

}
