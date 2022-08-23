package com.kata.stats;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatsCalculatorTest {

    private final StatsCalculator statsCalculator = new StatsCalculator();

    /**
     * Your task is to process a sequence of integer numbers
     * to determine the following statistics:
     * <p>
     * o) minimum value
     * o) maximum value
     * o) number of elements in the sequence
     * o) average value
     * <p>
     * For example: [6, 9, 15, -2, 92, 11]
     * <p>
     * o) minimum value = -2
     * o) maximum value = 92
     * o) number of elements in the sequence = 6
     * o) average value = 18.166666 Wrong
     */

    @Test
    void should_getStats() {
        int[] input = {6, 9, 15, -2, 92, 11};
        String average = "average";
        Map<String, Object> expected = Map.of(

                "min", -2,
                "max", 92,
                "count", 6,
                "average", 21.833334f);

        Map<String, Object> result = statsCalculator.getStats(input);
        assertEquals(expected.get(average), result.get(average));
    }

    @Test
    void should_get_empty_stats() {
        int[] input = {};
        String average = "average";
        Map<String, Object> expected = Map.of(

                "min", 0,
                "max", 0,
                "count", 0,
                "average", 0);

        Map<String, Object> result = statsCalculator.getStats(input);
        assertEquals(expected.get(average), result.get(average));
    }

    @Test
    void should_tell_min_value() {
        int[] input = {12, 88, 6, 7};
        Map<String, Object> expected = Map.of("min", 6);

        Map<String, Object> result = statsCalculator.getStats(input);
        assertEquals(expected.get("min"), result.get("min"));
    }

    @Test
    void should_tell_min_value_with_negative() {
        int[] input = {12, -88, 6, 7};
        Map<String, Object> expected = Map.of("min", -88);

        Map<String, Object> result = statsCalculator.getStats(input);
        assertEquals(expected.get("min"), result.get("min"));
    }

    @Test
    void should_tell_max_value() {
        int[] input = {12, 88, 6, 7};
        String maxValueKey = "max";
        Map<String, Object> expected = Map.of(maxValueKey, 88);

        Map<String, Object> result = statsCalculator.getStats(input);
        assertEquals(expected.get(maxValueKey), result.get(maxValueKey));
    }

    @Test
    void should_tell_elements_count() {
        int[] input = {12, 88, 6, 7};
        String elementsCount = "count";
        Map<String, Object> expected = Map.of(elementsCount, 4);

        Map<String, Object> result = statsCalculator.getStats(input);
        assertEquals(expected.get(elementsCount), result.get(elementsCount));
    }

    @Test
    void should_tell_average() {
        int[] input = {1, 2, 3, 4, 5};
        String average = "average";
        Map<String, Object> expected = Map.of(average, 3.0);

        Map<String, Object> result = statsCalculator.getStats(input);
        assertEquals(expected.get(average).toString(), result.get(average).toString());
    }
}
