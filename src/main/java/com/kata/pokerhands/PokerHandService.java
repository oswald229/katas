package com.kata.pokerhands;


public class PokerHandService {

    private final PokerPrinter printer = new PokerConsolePrinter();
    private final Dealer dealer = new Dealer();

    public String winner(String black, String white) {
        var blackHand = new PokerHand(PokerHandReader.parseHand(black));
        var whiteHand = new PokerHand(PokerHandReader.parseHand(white));

        if (blackHand.equals(whiteHand)) {
            var winningCard = Dealer.getWinningCard(blackHand, whiteHand);
            return printer.printWinningCard(winningCard);
        }
        var winningHand = dealer.getWinningHand(blackHand, whiteHand);
        return printer.printWinningHand(winningHand);
    }
}
