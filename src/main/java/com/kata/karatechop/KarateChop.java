package com.kata.karatechop;

import java.util.List;

public class KarateChop {
    private KarateChop(){}

    public static int chop(int i, List<Integer> ints) {

        if (ints.isEmpty()) {
            return -1;
        }

        int mid = ints.size() / 2;

        if (i == ints.get(mid)) {
            return mid;
        }

        if (i > ints.get(mid) && ints.size() > 1) {
            List<Integer> subList = ints.subList(mid, ints.size());
            int chop = chop(i, subList);
            return chop == -1 ? -1 : chop + mid;
        }

        if (i < ints.get(mid) && ints.size() > 1) {
            List<Integer> subList = ints.subList(0, mid);
            return chop(i, subList);
        }
        return -1;
    }

}
