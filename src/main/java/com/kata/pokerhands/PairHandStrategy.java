package com.kata.pokerhands;

class PairHandStrategy implements HandStrategy {
    @Override
    public boolean matches(Cards cards) {
        return isPair(cards);
    }

    @Override
    public PokerHandEnum hand() {
        return PokerHandEnum.PAIR;
    }

    private static boolean isPair(Cards cards) {
        var groupedCard = cards.groupedByCardValue();
        return groupedCard.size() == 4 && groupedCard.containsValue(2);
    }
}
