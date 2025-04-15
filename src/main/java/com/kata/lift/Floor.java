package com.kata.lift;

public record Floor(int level) implements Comparable<Floor> {
    
    public Direction to(Floor floor) {
        return this.level < floor.level ? Direction.UP : Direction.DOWN;
    }

    @Override
    public int compareTo(Floor o) {
        return Integer.compare(this.level, o.level);
    }
}
