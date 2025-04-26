package com.kata.pokerhands;

public abstract class PokerGame<T, U> {
    protected final PokerRenderer<U> printer;
    protected final CardsParser<T> handParser;
    protected final Dealer dealer;

    protected PokerGame(PokerRenderer<U> printer,
                        CardsParser<T> handParser, Dealer dealer) {
        this.printer = printer;
        this.handParser = handParser;
        this.dealer = dealer;
    }

    abstract U winner(T black, T white);
}
