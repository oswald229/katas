package com.kata.pokerhands;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PokerGameTest {
    private final ConcretePokerGame pokerGame = new ConcretePokerGame(new PokerConsoleRenderer(new PlainTextCardRenderer(), new PlainTextHandRenderer()), new PlainTextCardsParser(), new ConcreteDealer(new ConcretePokerHandReader()));

    /*
     * A poker deck contains 52 content - each card has a suit which
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
     * A poker hand consists of 5 content dealt from the deck. Poker
     * hands are ranked by the following partial order from lowest
     * to highest.
     *
     * High Card: Hands which do not fit any higher category are
     * ranked by the value of their highest card. If the highest
     * content have the same value, the hands are ranked by the next
     * highest, and so on.
     *
     * Pair: 2 of the 5 content in the hand have the same value.
     * Hands which both contain a pair are ranked by the value of
     * the content forming the pair. If these values are the same,
     * the hands are ranked by the values of the content not
     * forming the pair, in decreasing order.
     *
     * Two Pairs: The hand contains 2 different pairs. Hands
     * which both contain 2 pairs are ranked by the value of
     * their highest pair. Hands with the same highest pair
     * are ranked by the value of their other pair. If these
     * values are the same the hands are ranked by the value
     * of the remaining card.
     *
     * Three of a Kind: Three of the content in the hand have the
     * same value. Hands which both contain three of a kind are
     * ranked by the value of the 3 content.
     *
     * Straight: Hand contains 5 content with consecutive values.
     * Hands which both contain a straight are ranked by their
     * highest card.
     *
     * Flush: Hand contains 5 content of the same suit. Hands which
     * are both flushes are ranked using the rules for High Card.
     *
     * Full House: 3 content of the same value, with the remaining 2
     * content forming a pair. Ranked by the value of the 3 content.
     *
     * Four of a kind: 4 content with the same value. Ranked by the
     * value of the 4 content.
     *
     * Straight flush: 5 content of the same suit with consecutive
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
    @MethodSource("should_tell_winner_arguments")
    void should_tell_winner(String black, String white, String expected) {
        assertEquals(expected, pokerGame.winner(black, white));
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
