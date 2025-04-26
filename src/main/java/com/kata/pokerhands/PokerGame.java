package com.kata.pokerhands;

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

    abstract U winner(T black, T white);
}
