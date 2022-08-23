package com.kata.pokerhands;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PokerHandParserTest {
    @Test
    void should_parse_card_string() {
        String card = "7H";

        Card expected = Card.builder()
                .value(CardValue.SEVEN)
                .suit(Suit.HEARTS)
                .build();

        assertEquals(expected, PokerHandParser.mapCard(card));
    }

    @Test
    void should_parse_poker_hand() {
        String hand = "7H QS 7C 3H 7D";
        List<Card> expected = List.of(
                Card.builder().suit(Suit.HEARTS).value(CardValue.SEVEN).build(),
                Card.builder().suit(Suit.SPADES).value(CardValue.QUEEN).build(),
                Card.builder().suit(Suit.CLUBS).value(CardValue.SEVEN).build(),
                Card.builder().suit(Suit.HEARTS).value(CardValue.THREE).build(),
                Card.builder().suit(Suit.DIAMONDS).value(CardValue.SEVEN).build()
        );
        assertEquals(expected, PokerHandParser.mapHand(hand));
    }

    @Test
    void should_sort_card_hand() {

        List<Card> unsorted = List.of(
                Card.builder().suit(Suit.DIAMONDS).value(CardValue.EIGHT).build(),
                Card.builder().suit(Suit.HEARTS).value(CardValue.TEN).build(),
                Card.builder().suit(Suit.CLUBS).value(CardValue.NINE).build(),
                Card.builder().suit(Suit.HEARTS).value(CardValue.SIX).build(),
                Card.builder().suit(Suit.SPADES).value(CardValue.SEVEN).build()
        );
        List<Card> expected = List.of(
                Card.builder().suit(Suit.HEARTS).value(CardValue.TEN).build(),
                Card.builder().suit(Suit.CLUBS).value(CardValue.NINE).build(),
                Card.builder().suit(Suit.DIAMONDS).value(CardValue.EIGHT).build(),
                Card.builder().suit(Suit.SPADES).value(CardValue.SEVEN).build(),
                Card.builder().suit(Suit.HEARTS).value(CardValue.SIX).build()
        );
        assertEquals(expected, PokerHandParser.sortHand(unsorted));
    }
}
