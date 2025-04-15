package com.kata.lift;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Floors(Set<Floor> floors) {

    Floor floor(int level) {
        return floors.stream()
                .filter(floor -> floor.level() == level)
                .findFirst().get();
    }

    public static Floors get(int i) {
        Set<Floor> floors = IntStream.range(0, i)
                .mapToObj(Floor::new)
                .collect(Collectors.toSet());
        return new Floors(floors);
    }

    public int levels() {
        return floors.size();
    }

    public Floor lowest() {
        return floors.stream().min(Floor::compareTo).get();
    }
}
