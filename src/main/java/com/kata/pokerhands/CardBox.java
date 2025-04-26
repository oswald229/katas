package com.kata.pokerhands;

import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class CardBox {
    private static final LinkedList<Card> cards = new LinkedList<>();
    private static final LinkedList<Card> drawn = new LinkedList<>();

    static {
        EnumSet.allOf(Suit.class).forEach(suit -> {
            for (CardValue value : CardValue.values()) {
                cards.add(new Card(suit, value));
            }
        });
    }

    private CardBox() {
    }

    public static void reset() {
        cards.addAll(drawn);
        drawn.removeAll(cards);
    }

    public static List<Card> getAll() {
        return Stream.of(cards, drawn).flatMap(Collection::stream).toList();
    }

    public static List<Card> getPokerHand() {
        return draw(5);
    }

    private static List<Card> draw(int amount) {
        if (cards.size() < amount) {
            reset();
            throw new CardBoxException("Not enough cards left.");
        }
        Collections.shuffle(CardBox.cards);
        List<Card> hand = new ArrayList<>(CardBox.cards.subList(0, amount));
        drawn.addAll(hand);
        CardBox.cards.removeAll(drawn);
        return hand;
    }

    public static List<List<Card>> getPokerHands(int player) {
        return getHands(player, 5);
    }

    public static List<List<Card>> getHands(int players, int cardPerPlayer) {

        return LongStream.rangeClosed(1, players)
                .boxed()
                .map(player -> draw(cardPerPlayer))
                .toList();
    }

    public static Card getCard(String cardString) {
        for (Card card : cards) {
            if (card.cardString().equals(cardString))
                return card;
        }
        throw new CardBoxException("Unknown card.");
    }
}
