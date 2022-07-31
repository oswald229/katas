package com.kata.gameoflife;

import java.util.Objects;

public class Cell {
    private final int x;
    private final int y;
    private CellStatus status = CellStatus.DEAD;

    public Cell(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public Cell(int x, int y, String s) {
        this(x,y);
        if(s.equals("*")){
            status = CellStatus.ALIVE;
        }


    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus nextGeneration) {
        this.status = nextGeneration;
    }
}
