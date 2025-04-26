package com.kata.pokerhands;

public abstract class PokerGame<T, U> {
    protected final PokerRenderer<U> printer;
    protected final CardParser<T> handParser;
    protected final Dealer dealer;

    protected PokerGame(PokerRenderer<U> printer,
                        CardParser<T> handParser, Dealer dealer) {
        this.printer = printer;
        this.handParser = handParser;
        this.dealer = dealer;
    }

    abstract U winner(T black, T white);
}
