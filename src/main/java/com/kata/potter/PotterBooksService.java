package com.kata.potter;

import com.kata.permutations.Permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PotterBooksService {

    public List<PotterCart> findCarts(List<PotterBooks> rawCart) {

        List<PotterCart> carts = new ArrayList<>();

        Set<List<PotterBooks>> permutations = new HashSet<>(Permutations.getPermutations(rawCart));
        
        permutations.forEach(permutation -> {

            for (int i = 1; i <= permutation.size(); i++) {
                List<Set<PotterBooks>> potterGroup = GroupsUtils.createGroups(permutation, i);
                PotterCart cart = buildCart(potterGroup);

                boolean correctSize = cart.totalItems() == rawCart.size(); // TODO : Avoid this condition.
                if (correctSize && !carts.contains(cart)) {
                    carts.add(cart);
                }
            }
        });

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
