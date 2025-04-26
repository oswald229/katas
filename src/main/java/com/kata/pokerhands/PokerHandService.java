package com.kata.pokerhands;


public record PokerHandService(PokerPrinter printer, Dealer dealer) {

    public String winner(String black, String white) {
        var blackHand = new PokerHand(PokerHandReader.parseHand(black));
        var whiteHand = new PokerHand(PokerHandReader.parseHand(white));
        Winner winner = dealer.getWinner(blackHand, whiteHand);
        return printer.printWinner(winner);
    }
}
