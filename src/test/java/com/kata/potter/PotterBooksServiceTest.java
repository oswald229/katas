package com.kata.potter;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PotterBooksServiceTest {
    private final PotterBooksService potterBookService = new PotterBooksService();

    /*
    To try and encourage more sales of the 5 different Harry
Potter books they sell, a bookshop has decided to offer
discounts of multiple-book purchases.

One copy of the five books costs 8 EUR.

If, however, you buy two different books, you get a 5%
discount on those two books.

If you buy 3 different books, you get a 10% discount.

If you buy 4 different books, you get a 20% discount.

If you go the whole hog, and buy all 5, you get a huge 25%
discount.

Note that if you buy, say, four books, of which 3 are
different titles, you get a 10% discount on the 3 that
form part of a set, but the fourth book still costs 8 EUR.

Your mission is to write a piece of code to calculate the
price of any conceivable shopping basket (containing only
Harry Potter books), giving as big a discount as possible.

For example, how much does this basket of books cost?

2 copies of the first book
2 copies of the second book
2 copies of the third book
1 copy of the fourth book
1 copy of the fifth book

Answer: 51.20 EUR
     */

    @Test
    void should_tell_conceivable_shopping_carts() {

        List<PotterBooks> rawCart = List.of(
                PotterBooks.ONE, PotterBooks.TWO
        );

        PotterCart cart1 = PotterCart.builder()
                .cartContent(
                        List.of(Set.of(PotterBooks.ONE, PotterBooks.TWO))
                )
                .build();

        /*--------------------------------------------------------*/
        PotterCart cart2 = PotterCart.builder()
                .cartContent(
                        List.of(Set.of(PotterBooks.ONE), Set.of(PotterBooks.TWO))
                )
                .build();
        /*--------------------------------------------------------*/

        PotterCart cart3 = PotterCart.builder()
                .cartContent(
                        List.of(Set.of(PotterBooks.TWO), Set.of(PotterBooks.ONE))
                )
                .build();

        List<PotterCart> expected = List.of(cart1, cart2, cart3);

        List<PotterCart> result = potterBookService.findCarts(rawCart);

        assertEquals(expected.size(), result.size());
        assertTrue(expected.containsAll(result));

    }

    @Test
    void compute_price() {

        List<PotterBooks> boughtBooks = List.of(
                PotterBooks.ONE, PotterBooks.ONE,
                PotterBooks.TWO, PotterBooks.TWO,
                PotterBooks.THREE, PotterBooks.THREE,
                PotterBooks.FOUR,
                PotterBooks.FIVE
        );
        assertEquals(51.20, potterBookService.bestPrice(boughtBooks));
    }

}
