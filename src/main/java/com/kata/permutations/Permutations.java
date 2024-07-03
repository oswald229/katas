package com.kata.permutations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

public class Permutations {

    private Permutations() {
    }

    public static <T> Set<List<T>> getPermutations(List<T> list) {
        Set<List<T>> result = new HashSet<>();

        if (list.size() == 2) {

            result.add(new ArrayList<>(list));
            ArrayList<T> second = new ArrayList<>();
            second.add(list.get(1));
            second.add(list.get(0));
            result.add(second);
            return result;
        }

        List<T> inputCopy = new ArrayList<>(list);

        for (int i = 0; i < list.size(); i++) {

            T current = inputCopy.remove(i);

            Set<List<T>> permutations = getPermutations(inputCopy);

            for (List<T> permutation : permutations) {
                ArrayList<T> integers = new ArrayList<>(permutation);
                integers.add(0, current);
                //TODO : Avoid duplicates creation.
                result.add(integers);
            }

            inputCopy.add(i, current);
        }
        return result;
    }

    public static long count(List<?> inputs, long items) {
        int size = inputs.size();
        return factorial(size) / factorial(size - items);
    }

    public static long count(List<?> list) {
        return count(list, list.size());
    }

    private static long factorial(long i) {
        return LongStream.rangeClosed(1, i)
                .reduce(1, (left, right) -> left * right);
    }
}
