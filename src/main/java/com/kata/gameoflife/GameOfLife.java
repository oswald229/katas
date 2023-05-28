package com.kata.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class GameOfLife {

    private final int height;
    private final int width;
    private List<Cell> cells = new ArrayList<>();


    public GameOfLife(String board) {

        String[] boardLines = board.split("\n");
        height = boardLines.length;
        width = boardLines[0].split("").length;
        initCells(boardLines);
    }

    private void initCells(String[] boardLines) {

        for (int i = 0; i < boardLines.length; i++) {

            String[] line = boardLines[i].split("");

            for (int j = 0; j < line.length; j++) {

                cells.add(new Cell(j, i, line[j]));

            }

        }

    }

    public Cell getCell(int x, int y) {
        verify(x, y);
        Cell toFind = new Cell(x, y);
        return this.cells.stream()
                .filter(
                        cell -> cell.equals(toFind))
                .findFirst()
                .orElseThrow();
    }

    private void verify(int x, int y) {
        if (x >= width || x < 0 || y >= height || y < 0) throw new IndexOutOfBoundsException();
    }

    public List<Cell> getCellNeighbours(int x, int y) {

        List<Cell> neighbours = new ArrayList<>();

        //Top
        if (y + 1 < height) {
            neighbours.add(
                    this.getCell(x, y + 1)
            );
        }

        //End
        if (y - 1 >= 0) {
            neighbours.add(
                    this.getCell(x, y - 1));
        }

        //Left
        if (x - 1 >= 0) {
            neighbours.add(
                    this.getCell(x - 1, y)
            );
        }

        //Right
        if (x + 1 < width) {
            neighbours.add(
                    this.getCell(x + 1, y));
        }

        // N-E
        if (y - 1 >= 0 && x + 1 < width) {
            neighbours.add(
                    this.getCell(x + 1, y - 1));
        }

        //N-W
        if (y - 1 >= 0 && x - 1 >= 0) {
            neighbours.add(
                    this.getCell(x - 1, y - 1));
        }

        //S-E
        if (y + 1 < height && x + 1 < width) {
            neighbours.add(
                    this.getCell(x + 1, y + 1));
        }

        //S-W
        if (y + 1 < height && x - 1 >= 0) {
            neighbours.add(
                    this.getCell(x - 1, y + 1));
        }

        return neighbours;
    }

    public String printBoard() {

        StringJoiner board = new StringJoiner("\n");

        StringBuilder boardLine = new StringBuilder();

        for (int i = 0; i < cells.size(); i++) {
            if (i % width == 0 && i > 0) {
                board.add(boardLine.toString());
                boardLine = new StringBuilder();
            }
            if (cells.get(i).getStatus() == CellStatus.ALIVE) {
                boardLine.append(CellStatus.ALIVE.getValue());
            } else {
                boardLine.append(CellStatus.DEAD.getValue());
            }
        }
        board.add(boardLine.toString());
        return board.toString();
    }

    public CellStatus nextGeneration(int x, int y) {
        Cell cell = this.getCell(x, y);

        List<Cell> cellNeighbours = this.getCellNeighbours(cell.getX(),
                cell.getY());

        long aliveNeighbours = cellNeighbours.stream()
                .filter(cell1 -> cell1.getStatus().equals(CellStatus.ALIVE))
                .count();

        //Any dead cell with exactly three live neighbours becomes a live cell.
        if (cell.getStatus().equals(CellStatus.DEAD)
                && aliveNeighbours == 3) {
            return CellStatus.ALIVE;
        }

        //Any live cell with two or three live neighbours lives on to the next generation.
        if (cell.getStatus().equals(CellStatus.ALIVE)
                && (aliveNeighbours == 3 || aliveNeighbours == 2)) {
            return CellStatus.ALIVE;
        }

        //Any live cell with more than three live neighbours dies, as if by overcrowding.
        if (cell.getStatus().equals(CellStatus.ALIVE)
                && (aliveNeighbours > 3)) {
            return CellStatus.DEAD;
        }

        //Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
        if (cell.getStatus().equals(CellStatus.ALIVE)) {
            return CellStatus.DEAD;
        }

        return cell.getStatus();
    }

    public String nextGeneration() {

        List<Cell> newGeneration = new ArrayList<>();

        for (Cell cell : cells) {
            CellStatus cellStatus = this.nextGeneration(cell.getX(), cell.getY());

            newGeneration.add(
                    new Cell(cell.getX(), cell.getY(), cellStatus.getValue()));

        }
        this.cells = newGeneration;
        return this.printBoard();
    }
}
