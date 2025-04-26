package com.kata.pokerhands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PokerHandServiceTest {
    private final PokerHandService pokerHandService = new PokerHandService();

    /*
     * A poker deck contains 52 cards - each card has a suit which
     * is one of clubs, diamonds, hearts, or spades
     * (denoted C, D, H, and S in the input data).
     *
     * Each card also has a value which is one of
     * 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, king, ace
     * (denoted 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A).
     *
     * For scoring purposes, the suits are unordered while the
     * values are ordered as given above, with 2 being the lowest
     * and ace the highest value.
     *
     * A poker hand consists of 5 cards dealt from the deck. Poker
     * hands are ranked by the following partial order from lowest
     * to highest.
     *
     * High Card: Hands which do not fit any higher category are
     * ranked by the value of their highest card. If the highest
     * cards have the same value, the hands are ranked by the next
     * highest, and so on.
     *
     * Pair: 2 of the 5 cards in the hand have the same value.
     * Hands which both contain a pair are ranked by the value of
     * the cards forming the pair. If these values are the same,
     * the hands are ranked by the values of the cards not
     * forming the pair, in decreasing order.
     *
     * Two Pairs: The hand contains 2 different pairs. Hands
     * which both contain 2 pairs are ranked by the value of
     * their highest pair. Hands with the same highest pair
     * are ranked by the value of their other pair. If these
     * values are the same the hands are ranked by the value
     * of the remaining card.
     *
     * Three of a Kind: Three of the cards in the hand have the
     * same value. Hands which both contain three of a kind are
     * ranked by the value of the 3 cards.
     *
     * Straight: Hand contains 5 cards with consecutive values.
     * Hands which both contain a straight are ranked by their
     * highest card.
     *
     * Flush: Hand contains 5 cards of the same suit. Hands which
     * are both flushes are ranked using the rules for High Card.
     *
     * Full House: 3 cards of the same value, with the remaining 2
     * cards forming a pair. Ranked by the value of the 3 cards.
     *
     * Four of a kind: 4 cards with the same value. Ranked by the
     * value of the 4 cards.
     *
     * Straight flush: 5 cards of the same suit with consecutive
     * values. Ranked by the highest card in the hand.
     *
     * Your job is to rank pairs of poker hands and to indicate
     * which, if either, has a higher rank.
     *
     * Examples:
     * Input: Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH
     * Output: White wins - high card: Ace
     *
     * Input: Black: 2H 4S 4C 3D 4H White: 2S 8S AS QS 3S
     * Output: White wins - flush
     *
     * Input: Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C KH
     * Output: Black wins - high card: 9
     *
     * Input: Black: 2H 3D 5S 9C KD White: 2D 3H 5C 9S KH
     * Output: Tie
     */


    @ParameterizedTest
    @MethodSource("should_tell_hand_arguments")
    void should_tell_hand(String hand, PokerHandEnum expected) {
        assertEquals(expected, new PokerHandReader().tellHand(hand));
    }

    @ParameterizedTest
    @MethodSource("should_tell_winner_arguments")
    void should_tell_winner(String black, String white, String expected) {
        assertEquals(expected, pokerHandService.winner(black, white));
    }

    @Test
    void should_tell_cards_stats() {

        List<Card> hand = List.of(
                Card.builder().value(CardValue.JACK).suit(Suit.HEARTS).build(),
                Card.builder().value(CardValue.JACK).suit(Suit.CLUBS).build(),
                Card.builder().value(CardValue.FIVE).suit(Suit.DIAMONDS).build(),
                Card.builder().value(CardValue.FIVE).suit(Suit.SPADES).build(),
                Card.builder().value(CardValue.SEVEN).suit(Suit.HEARTS).build()

        );

        Map<CardValue, Integer> expected = Map.of(
                CardValue.JACK, 2,
                CardValue.FIVE, 2,
                CardValue.SEVEN, 1
        );

        assertEquals(expected, PokerHandReader.getValueDistribution(hand));
    }

    private static Stream<Arguments> should_tell_hand_arguments() {
        return Stream.of(
                Arguments.of("KH 8C QD 2S 7H", PokerHandEnum.HIGH_CARD),
                Arguments.of("KH AC QD 2S 7H", PokerHandEnum.HIGH_CARD),
                Arguments.of("AH AC KD JS 7H", PokerHandEnum.PAIR),
                Arguments.of("AH KD JS AC 7H", PokerHandEnum.PAIR),
                Arguments.of("JH JC 5D 5S 7H", PokerHandEnum.TWO_PAIR),
                Arguments.of("5S JH 7H JC 5D", PokerHandEnum.TWO_PAIR),
                Arguments.of("7H 7D 7C QS 3H", PokerHandEnum.THREE_OF_A_KIND),
                Arguments.of("7H QS 7C 3H 7D", PokerHandEnum.THREE_OF_A_KIND),
                Arguments.of("TH 9C 8D 7S 6H", PokerHandEnum.STRAIGHT),
                Arguments.of("AH KC QD JS TH", PokerHandEnum.STRAIGHT),
                Arguments.of("AH KH QH JH 9H", PokerHandEnum.FLUSH),
                Arguments.of("AS KS QS JS 4S", PokerHandEnum.FLUSH),
                Arguments.of("AS AS 3S AS 3S", PokerHandEnum.FULL_HOUSE),
                Arguments.of("AS AS AS 3S 3S", PokerHandEnum.FULL_HOUSE),
                Arguments.of("9S 9S 9S 9S 3S", PokerHandEnum.FOUR_OF_A_KIND),
                Arguments.of("3S 3S 9S 3S 3S", PokerHandEnum.FOUR_OF_A_KIND),
                Arguments.of("JS TS 9S 8S 7S", PokerHandEnum.STRAIGHT_FLUSH),
                Arguments.of("9S 8S 7S 6S 5S", PokerHandEnum.STRAIGHT_FLUSH),
                Arguments.of("AS KS QS JS TS", PokerHandEnum.ROYAL_FLUSH),
                Arguments.of("AD KD QD JD TD", PokerHandEnum.ROYAL_FLUSH)
        );
    }


    private static Stream<Arguments> should_tell_winner_arguments() {
        return Stream.of(
                Arguments.of("2H 3D 5S 9C KD", "2C 3H 4S 8C AH", "White wins - high card: Ace"),
                Arguments.of("2H 4S 4C 3D 4H", "2S 8S AS QS 3S", "White wins - flush"),
                Arguments.of("2H 3D 5S 9C KD", "2C 3H 4S 8C KH", "Black wins - high card: 9"),
                Arguments.of("AH KH QH JH TH", "2S 8S AS QS 3S", "Black wins - royal flush"),
                Arguments.of("2H 3D 5S 9C KD", "2D 3H 5C 9S KH", "Tie")
        );
    }

}
