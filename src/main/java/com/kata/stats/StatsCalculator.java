package com.kata.stats;

import java.util.HashMap;
import java.util.Map;

public class StatsCalculator {
    public Map<String, Object> getStats(int[] input) {
        Map<String, Object> result = new HashMap<>(Map.of(
                "min", 0,
                "max", 0,
                "count", 0,
                "average", 0
        ));

        if (input.length > 0) {

            int minA = Integer.MAX_VALUE;
            int maxA = Integer.MIN_VALUE;
            float sum = 0;

            for (int i : input) {

                sum += i;
                if (minA >= i)
                    minA = i;
                if (maxA <= i)
                    maxA = i;
            }
            result.put("min", minA);
            result.put("max", maxA);
            result.put("count", input.length);
            result.put("average", sum / input.length);

            return result;
        }
        return result;
    }
}
