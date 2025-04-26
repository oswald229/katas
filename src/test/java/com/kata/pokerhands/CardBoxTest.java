package com.kata.pokerhands;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardBoxTest {
    @Test
    void should_return_standard_cards_amount() {
        List<Card> result = CardBox.getAll();
        assertEquals(52, result.size());
    }

    @Test
    void should_not_have_duplicate() {
        Card card = new Card(Suit.HEARTS, CardValue.SEVEN);
        List<Card> result = CardBox.getAll();
        assertEquals(1, Collections.frequency(result, card));
    }

    @Test
    void should_return_poker_hand() {
        List<Card> result = CardBox.getPokerHand();
        assertEquals(5, result.size());
    }

    @Test
    void should_not_have_duplicate_cards_in_multiple_hands() {
        List<List<Card>> hands = CardBox.getPokerHands(2);

        List<Card> hand1 = hands.get(0);
        List<Card> hand2 = hands.get(1);

        long duplicates = hand1.stream()
                .filter(hand2::contains)
                .count();

        assertEquals(0, duplicates);
    }

    @Test
    void should_throw_when_no_more_hand_to_give() {
        Exception exception = assertThrows(CardBoxException.class, () -> CardBox.getPokerHands(11));
        assertEquals("Not enough content left.", exception.getMessage());
    }


    @Test
    void should_get_hand_for_each_player() {
        int players = 4;
        int cardPerPlayer = 7;

        List<List<Card>> hands = CardBox.getHands(players, cardPerPlayer);

        assertEquals(players, hands.size());

        for (List<Card> hand : hands) {
            assertEquals(cardPerPlayer, hand.size());
        }

    }

    @Test
    void should_return_card() {
        CardBox.reset();
        String card = "AS";
        Card result = CardBox.getCard(card);
        Card expected = Card.builder().value(CardValue.ACE).suit(Suit.SPADES).build();

        assertEquals(expected, result);
    }

}
