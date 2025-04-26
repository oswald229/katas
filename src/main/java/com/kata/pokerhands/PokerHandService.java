package com.kata.pokerhands;


public record PokerHandService(PokerPrinter printer, Dealer dealer) {

    public String winner(String black, String white) {
        var blackHand = new PokerHand(PokerHandReader.parseHand(black));
        var whiteHand = new PokerHand(PokerHandReader.parseHand(white));

        if (blackHand.equals(whiteHand)) {
            var winningCard = dealer.getWinningCard(blackHand, whiteHand);
            return printer.printWinningCard(winningCard);
        }
        var winningHand = dealer.getWinningHand(blackHand, whiteHand);
        return printer.printWinningHand(winningHand);
    }
}
