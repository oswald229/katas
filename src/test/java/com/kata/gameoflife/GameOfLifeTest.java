package com.kata.gameoflife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameOfLifeTest {
    private GameOfLife gameOfLife;

    /**
     * Your task is to write a program to calculate the next
     * generation of Conway's game of life, given any starting
     * position. You start with a two-dimensional grid of cells,
     * where each cell is either alive or dead. The grid is finite,
     * and no life can exist off the edges. When calculating the
     * next generation of the grid, follow these four rules:
     * <p>
     * 1. Any live cell with fewer than two live neighbours dies,
     * as if caused by underpopulation.
     * 2. Any live cell with more than three live neighbours dies,
     * as if by overcrowding.
     * 3. Any live cell with two or three live neighbours lives
     * on to the next generation.
     * 4. Any dead cell with exactly three live neighbours becomes
     * a live cell.
     * <p>
     * Examples: * indicates live cell, . indicates dead cell
     * <p>
     * Example input: (4 x 8 grid)
     * 4 8
     * ........
     * ....*...
     * ...**...
     * ........
     * <p>
     * Example output:
     * 4 8
     * ........
     * ...**...
     * ...**...
     * ........
     */

    @BeforeEach
    private void setup() {
        String input = """      
                ........
                ....*...
                ...**...
                ........""".trim();
        gameOfLife = new GameOfLife(input);
    }


    @Test
    void should_build_game_board_cells() {
        Cell expected = new Cell(0, 0);
        assertEquals(expected, gameOfLife.getCell(0, 0));
    }

    @Test
    void should_build_game_board_cells_bis() {
        Cell expected = new Cell(2, 0);
        assertEquals(expected, gameOfLife.getCell(2, 0));
    }

    @Test
    void should_return_cells_neighbours() {

        Cell firstNeighbour = new Cell(1, 0);
        Cell secondNeighbour = new Cell(0, 1);
        Cell thirdNeighbour = new Cell(1, 1);


        List<Cell> expected = List.of(
                firstNeighbour, secondNeighbour, thirdNeighbour
        );

        List<Cell> result = gameOfLife.getCellNeighbours(0, 0);

        assertEquals(expected.size(), result.size());
        assertTrue(expected.containsAll(result));

    }

    @Test
    void should_print_board() {
        String input = """      
                ........
                ....*...
                ...**...
                ........""".trim();

        assertEquals(input, gameOfLife.printBoard());
    }

    @Test
    void should_tell_cell_status() {
        assertEquals(CellStatus.ALIVE, gameOfLife.getCell(4, 1).getStatus());
        assertEquals(CellStatus.ALIVE, gameOfLife.getCell(3, 2).getStatus());
        assertEquals(CellStatus.DEAD, gameOfLife.getCell(0, 3).getStatus());
    }

    @Test
    void should_tell_next_generation_status_for_a_cell() {
        assertEquals(CellStatus.ALIVE, gameOfLife.nextGeneration(3, 1));
    }

    @Test
    void should_print_next_generation() {
        String expected = """      
                ........
                ...**...
                ...**...
                ........""".trim();

        assertEquals(expected, gameOfLife.nextGeneration());
    }

    @Test
    void should_print_next_generation_bis() {
        String expected = """      
                ........
                ........
                ........
                ........""".trim();
        gameOfLife = new GameOfLife(expected);

        assertEquals(expected, gameOfLife.nextGeneration());
    }

    @Test
    void should_print_next_generation_ter() {
        String input = """      
                ********
                ********
                ********
                ********""".trim();
        gameOfLife = new GameOfLife(input);

        String expected = """      
                *......*
                ........
                ........
                *......*""".trim();

        assertEquals(expected, gameOfLife.nextGeneration());
    }
}
