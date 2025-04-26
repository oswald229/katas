package com.kata.pokerhands;


public class ConcretePokerGame extends PokerGame<String, String> {

    public ConcretePokerGame(PokerRenderer<String> printer, CardsParser<String> handParser,
                             Dealer dealer) {
        super(printer, handParser, dealer);
    }

    @Override
    public String winner(String black, String white) {
        var blackHand = handParser.parse(black);
        var whiteHand = handParser.parse(white);
        var winner = dealer.getWinner(blackHand, whiteHand);
        return printer.printWinner(winner);
    }
}
