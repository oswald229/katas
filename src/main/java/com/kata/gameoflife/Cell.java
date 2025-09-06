package com.kata.gameoflife;

public class Cell {
    private final int x;
    private final int y;
    private CellStatus status = CellStatus.DEAD;

    public Cell(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public Cell(int x, int y, String s) {
        this(x, y);
        if (s.equals("*")) {
            status = CellStatus.ALIVE;
        }
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
        return super.hashCode();
    }

    public boolean isAlive() {
        return status.equals(CellStatus.ALIVE);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public CellStatus getStatus() {
        return this.status;
    }
}
