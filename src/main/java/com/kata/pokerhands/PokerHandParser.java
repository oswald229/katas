package com.kata.pokerhands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PokerHandParser {
    private PokerHandParser(){}
    public static Card mapCard(String cardString) {
        String[] split = cardString.trim().split("");
        String value = split[0];
        String suit = split[1];

        return Card.builder()
                .value(getCardValue(value))
                .suit(getCardSuit(suit))
                .build();
    }

    public static Suit getCardSuit(String suit) {
        return switch (suit) {
            case "C" -> Suit.CLUBS;
            case "D" -> Suit.DIAMONDS;
            case "H" -> Suit.HEARTS;
            default -> Suit.SPADES;
        };
    }

    public static CardValue getCardValue(String value) {

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

    public static List<Card> mapHand(String hand) {
        String[] cards = hand.split(" ");
        return Arrays.stream(cards).map(PokerHandParser::mapCard).toList();
    }

    public static List<Card> sortHand(List<Card> unsorted) {
        ArrayList<Card> cards = new ArrayList<>(unsorted);
        Collections.sort(cards);
        Collections.reverse(cards);
        return cards;
    }
}
