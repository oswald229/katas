package com.kata.pokerhands;

import lombok.SneakyThrows;
import org.apache.commons.lang.math.IntRange;

import java.util.*;
import java.util.stream.Stream;

public class CardBox {
    private static final List<Card> cards = new ArrayList<>();
    private static final List<Card> drawn = new ArrayList<>();

    static {
        EnumSet.allOf(Suit.class).forEach(suit -> {
            for (CardValue value : CardValue.values()) {
                cards.add(new Card(suit, value));
            }
        });
    }

    private CardBox() {
    }

    private static void reset() {
        cards.addAll(drawn);
        drawn.removeAll(cards);
    }

    public static List<Card> getAll() {
        return Stream.of(cards, drawn).flatMap(Collection::stream).toList() ;
    }

    public static List<Card> getPokerHand() {
        return draw(5);
    }

    @SneakyThrows
    private static List<Card> draw(int amount) {
        if (cards.size() < amount) {
            reset();
            throw new Exception("Not enough cards left.");
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

        return Arrays.stream(new IntRange(1, players).toArray())
                .boxed()
                .map(player -> draw(cardPerPlayer))
                .toList();
    }
}
