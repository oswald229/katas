package com.kata.pokerhands;


public record PokerHandService(PokerPrinter printer,PokerHandParser handParser, Dealer dealer) {


    public String winner(String black, String white) {
        var blackHand = new PokerHand(handParser.parse(black));
        var whiteHand = new PokerHand(handParser.parse(white));
        Winner winner = dealer.getWinner(blackHand, whiteHand);
        return printer.printWinner(winner);
    }
}
