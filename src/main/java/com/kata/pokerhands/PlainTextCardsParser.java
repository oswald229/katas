package com.kata.pokerhands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlainTextCardsParser implements CardsParser<String> {

    @Override
    public PokerHand parse(String hand) {
        var cards = new Cards(sortHand(Arrays.stream(hand.split(" ")).
                map(this::mapCard)
                .toList()));
        return new PokerHand(cards);
    }

    private List<Card> sortHand(List<Card> unsorted) {
        var cards = new ArrayList<>(unsorted);
        Collections.sort(cards);
        Collections.reverse(cards);
        return cards;
    }

    private Card mapCard(String cardString) {
        String[] split = cardString.trim().split("");
        String value = split[0];
        String suit = split[1];
        return Card.builder()
                .value(getCardValue(value))
                .suit(getCardSuit(suit))
                .build();
    }

    private static Suit getCardSuit(String suit) {
        return switch (suit) {
            case "C" -> Suit.CLUBS;
            case "D" -> Suit.DIAMONDS;
            case "H" -> Suit.HEARTS;
            default -> Suit.SPADES;
        };
    }

    private static CardValue getCardValue(String value) {

        return switch (value) {
            case "A" -> CardValue.ACE;

            case "K" -> CardValue.KING;

            case "Q" -> CardValue.QUEEN;

            case "J" -> CardValue.JACK;

            case "T" -> CardValue.TEN;

            default -> Arrays.stream(CardValue.values()).toList()
                    .get(Integer.parseInt(value) - 2);

        };
    }
}
