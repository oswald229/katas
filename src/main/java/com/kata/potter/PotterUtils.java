package com.kata.potter;


import com.kata.permutations.Permutations;

import java.util.*;

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
        return Permutations.getPermutations(input)
                .stream()
                .parallel()
                .distinct()
                .toList();

    }
}
