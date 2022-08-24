package com.kata.potter;

import org.paukov.combinatorics3.Generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PotterUtils {

    private PotterUtils(){}

    public static List<Set<PotterBooks>> createPotterGroups(int n, List<PotterBooks> input) {
        if (n == 1) {
            return input.stream().map(Set::of).toList();
        }

        List<Set<PotterBooks>> result = new ArrayList<>();
        for (int i = 0; i + n - 1 < input.size(); i = i + n) {

            Set<PotterBooks> subGroup = new HashSet<>(input.subList(i, n + i));
            result.add(subGroup);

            boolean hasItemLeft = input.size() - (i + n) < n && input.size() - (i + n) != 0;
            if (hasItemLeft) {
                result.add(new HashSet<>(input.subList(i + n, input.size())));
            }

        }

        return result;
    }

    public static List<List<PotterBooks>> possiblePermutations(List<PotterBooks> input) {
        return Generator.permutation(input)
                .simple()
                .stream().parallel()
                .distinct()
                .toList();

    }
}
