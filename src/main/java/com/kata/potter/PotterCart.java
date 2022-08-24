package com.kata.potter;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Builder
@EqualsAndHashCode
public class PotterCart implements Comparable<PotterCart> {

    public static final double SINGLE_BOOK_PRICE = 8;
    List<Set<PotterBooks>> cartContent;

    public double getTotalPrice() {

        return cartContent.stream().map(
                getSetPrice()
        ).reduce((double) 0, Double::sum);

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

    public int compareByPrice(PotterCart o){
        return this.compareTo(o);
    }
    @Override
    public int compareTo(PotterCart o) {
        return Double.compare(this.getTotalPrice(), o.getTotalPrice());
    }
}
