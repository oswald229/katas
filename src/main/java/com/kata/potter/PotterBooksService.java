package com.kata.potter;

import com.kata.permutations.Permutations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PotterBooksService {

    public List<PotterCart> findCarts(List<PotterBooks> rawCart) {

        List<PotterCart> carts = new ArrayList<>();

        List<List<PotterBooks>> permutations = Permutations.getPermutations(rawCart);

        for (List<PotterBooks> permutation : permutations) {

            for (int i = 1; i <= permutation.size(); i++) {
                List<Set<PotterBooks>> potterGroup = PotterUtils.createPotterGroups(i, permutation.stream().toList());

                PotterCart cart = buildCart(potterGroup);
                if (cart.totalItems() == rawCart.size() && !carts.contains(cart)) {
                    carts.add(cart);
                }
            }
        }
        return carts;
    }

    private static PotterCart buildCart(List<Set<PotterBooks>> potterGroups) {

        return PotterCart.builder()
                .cartContent(potterGroups)
                .build();
    }

    public double bestPrice(List<PotterBooks> boughtBooks) {
        return findCarts(boughtBooks)
                .parallelStream()
                .min(PotterCart::compareByPrice)
                .orElseThrow().getTotalPrice();
    }
}
