package com.kata.potter;


import lombok.SneakyThrows;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class GroupsUtils {

    private GroupsUtils() {
    }

    @SneakyThrows
    public static <T> List<Set<T>> createGroups(List<T> input, int itemsInGroup) {

        int inputSize = input.size();
        if (itemsInGroup > inputSize) {
            throw new GroupsException();
        }

        List<Set<T>> result = new ArrayList<>();
        for (int i = 0; i + itemsInGroup - 1 < inputSize; i = i + itemsInGroup) {

            Set<T> subGroup = new HashSet<>(input.subList(i, i + itemsInGroup));
            result.add(subGroup);

            int leftItemsCount = inputSize - (i + itemsInGroup);
            boolean hasItemsLeft = leftItemsCount < itemsInGroup && leftItemsCount != 0;
            if (hasItemsLeft) {
                result.add(new HashSet<>(input.subList(i + itemsInGroup, inputSize)));
            }
        }
        return result;
    }

}
