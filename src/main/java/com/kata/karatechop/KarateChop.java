package com.kata.karatechop;

import java.util.List;

public class KarateChop {
    private KarateChop() {
    }

    public static int chop(int target, List<Integer> searchInput) {

        if (searchInput.isEmpty()) {
            return -1;
        }

        int middleIndex = searchInput.size() / 2;

        if (target == searchInput.get(middleIndex)) {
            return middleIndex;
        }

        if (searchInput.size() > 1) {

            if (target > searchInput.get(middleIndex)) {
                List<Integer> inputGreaterHalf = searchInput.subList(middleIndex, searchInput.size());
                int chop = chop(target, inputGreaterHalf);
                return chop == -1 ? -1 : chop + middleIndex;
            }

            if (target < searchInput.get(middleIndex)) {
                List<Integer> inputSmallerHalf = searchInput.subList(0, middleIndex);
                return chop(target, inputSmallerHalf);
            }
        }
        return -1;
    }

}
