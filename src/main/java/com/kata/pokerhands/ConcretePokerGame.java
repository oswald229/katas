package com.kata.pokerhands;


public class ConcretePokerGame extends PokerGame<String, String> {

    public ConcretePokerGame(PokerRenderer<String> printer, CardParser<String> handParser,
                             Dealer dealer) {
        super(printer, handParser, dealer);
    }

    @Override
    public String winner(String black, String white) {
        var blackCards = handParser.parse(black);
        var witheCards = handParser.parse(white);
        var blackHand = new PokerHand(blackCards);
        var whiteHand = new PokerHand(witheCards);
        var winner = dealer.getWinner(blackHand, whiteHand);
        return printer.printWinner(winner);
    }
}
