package com.kata.gameoflife;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CellTest {


    @Test
    void should_have_x_and_y_coords() {

        int x = 5, y = 7;

        Cell cell = new Cell(x, y);
        assertEquals(x, cell.getX());
        assertEquals(y, cell.getY());

    }

    @Test
    void should_have_a_status() {

        Cell cell = new Cell(0,0, "*");

        assertEquals(CellStatus.ALIVE, cell.getStatus());

    }
}