package com.kata.pokerhands;


public record PokerHandService(PokerPrinter printer, CardParser handParser, Dealer dealer) {


    public String winner(String black, String white) {
        var blackCards = handParser.parse(black);
        var witheCards = handParser.parse(white);
        var blackHand = new PokerHand(blackCards);
        var whiteHand = new PokerHand(witheCards);
        var winner = dealer.getWinner(blackHand, whiteHand);
        return printer.printWinner(winner);
    }
}
