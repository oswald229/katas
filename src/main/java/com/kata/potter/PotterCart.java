package com.kata.potter;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class PotterCart implements Comparable<PotterCart> {

    private static final double SINGLE_BOOK_PRICE = 8;
    private List<Set<PotterBooks>> cartContent;

    PotterCart(List<Set<PotterBooks>> cartContent) {
        this.cartContent = cartContent;
    }

    public static PotterCartBuilder builder() {
        return new PotterCartBuilder();
    }

    public double getTotalPrice() {
        return cartContent.stream()
                .map(getSetPrice())
                .reduce((double) 0, Double::sum);
    }

    private static Function<Set<PotterBooks>, Double> getSetPrice() {
        return potterBooks ->
                switch (potterBooks.size()) {
                    case 5 -> SINGLE_BOOK_PRICE * 5 * 0.75;
                    case 4 -> SINGLE_BOOK_PRICE * 4 * 0.80;
                    case 3 -> SINGLE_BOOK_PRICE * 3 * 0.90;
                    case 2 -> SINGLE_BOOK_PRICE * 2 * 0.95;
                    default -> SINGLE_BOOK_PRICE;
                };
    }

    public int totalItems() {
        return cartContent
                .stream()
                .reduce(
                        0,
                        (current, potterBooks) -> potterBooks.size() + current,
                        Integer::sum);
    }

    public int compareByPrice(PotterCart o) {
        return this.compareTo(o);
    }

    @Override
    public int compareTo(PotterCart o) {
        return Double.compare(this.getTotalPrice(), o.getTotalPrice());
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PotterCart)) return false;
        final PotterCart other = (PotterCart) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$cartContent = this.cartContent;
        final Object other$cartContent = other.cartContent;
        if (this$cartContent == null ? other$cartContent != null : !this$cartContent.equals(other$cartContent))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PotterCart;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $cartContent = this.cartContent;
        result = result * PRIME + ($cartContent == null ? 43 : $cartContent.hashCode());
        return result;
    }

    public static class PotterCartBuilder {
        private List<Set<PotterBooks>> cartContent;

        PotterCartBuilder() {
        }

        public PotterCartBuilder cartContent(List<Set<PotterBooks>> cartContent) {
            this.cartContent = cartContent;
            return this;
        }

        public PotterCart build() {
            return new PotterCart(this.cartContent);
        }

        public String toString() {
            return "PotterCart.PotterCartBuilder(cartContent=" + this.cartContent + ")";
        }
    }
}
