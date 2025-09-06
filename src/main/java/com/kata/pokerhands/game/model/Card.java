package com.kata.pokerhands.game.model;

import java.util.Arrays;
import java.util.List;

public class Card implements Comparable<Card> {
    public static final Card EMPTY = Card.builder().build();
    private Suit suit;
    private CardValue value;

    Card(Suit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public static CardBuilder builder() {
        return new CardBuilder();
    }

    public boolean isNeighbourOf(Card card) {
        return CardValue.indexOf(getValue()) - CardValue.indexOf(card.getValue()) == 1;
    }

    @Override
    public int compareTo(Card other) {
        if (this.value.equals(other.value))
            return 0;
        List<CardValue> cardValues = Arrays.stream(CardValue.values()).toList();
        return cardValues.indexOf(value) < cardValues.indexOf(other.value) ? -1 : 1;
    }

    @Override
    public String toString() {
        return getValue().toString() + getSuit().toString();
    }

    public boolean isHigherThan(Card card) {
        return this.compareTo(card) > 0;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public CardValue getValue() {
        return this.value;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Card)) return false;
        final Card other = (Card) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$suit = this.getSuit();
        final Object other$suit = other.getSuit();
        if (this$suit == null ? other$suit != null : !this$suit.equals(other$suit)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Card;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $suit = this.getSuit();
        result = result * PRIME + ($suit == null ? 43 : $suit.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public static class CardBuilder {
        private Suit suit;
        private CardValue value;

        CardBuilder() {
        }

        public CardBuilder suit(Suit suit) {
            this.suit = suit;
            return this;
        }

        public CardBuilder value(CardValue value) {
            this.value = value;
            return this;
        }

        public Card build() {
            return new Card(this.suit, this.value);
        }

        public String toString() {
            return "Card.CardBuilder(suit=" + this.suit + ", value=" + this.value + ")";
        }
    }
}
