package com.kata.pokerhands.game;

import com.kata.pokerhands.game.io.CardsParser;
import com.kata.pokerhands.game.io.PokerGameRenderer;
import com.kata.pokerhands.game.model.Dealer;

public abstract class PokerGame<T, U> {
    protected final CardsParser<T> handParser;
    protected final PokerGameRenderer<U> printer;
    protected final Dealer dealer;

    protected PokerGame(CardsParser<T> handParser,
                        Dealer dealer,
                        PokerGameRenderer<U> printer) {
        this.printer = printer;
        this.handParser = handParser;
        this.dealer = dealer;
    }

    public abstract U winner(T black, T white);
}
