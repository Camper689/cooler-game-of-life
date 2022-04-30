package com.cgol.game;

import com.cgol.cell.EmptyCell;
import com.cgol.grid.ArrayCellGrid;
import com.cgol.grid.CellGrid;
import com.cgol.utils.CellCreator;

public class ConsoleGameOfLife implements GameOfLife {

    private static final CellCreator EMPTY_CELL_CREATOR = EmptyCell::new;

    private final CellGrid currentGrid;
    private final CellGrid tempGrid;

    public ConsoleGameOfLife(int width, int height) {
        this.currentGrid = new ArrayCellGrid(width, height, EMPTY_CELL_CREATOR);
        this.tempGrid = new ArrayCellGrid(width, height, EMPTY_CELL_CREATOR);
    }

    public void init() {
    }

    @Override
    public void evolve() {
        currentGrid.forEach((x, y) -> {
            currentGrid.getCell(x, y).evolve(
                    currentGrid.getNeighbours(x, y)
            );
            //tempGrid.setCell(newCell, x, y);
        });
    }

    @Override
    public CellGrid grid() {
        return currentGrid;
    }

    public void display() {
        final int[] lastY = {0};
        grid().forEach((x, y) -> {
            if(y != lastY[0]) {
                System.out.println();
                lastY[0] = y;
            }
            System.out.print("*");
        });
    }
}
