package com.cgol.game;

import com.cgol.cell.Cell;
import com.cgol.grid.ArrayCellGrid;
import com.cgol.grid.CellGrid;
import com.cgol.utils.CellCreator;

public class GameOfLife {

    private CellGrid currentGrid;
    private CellGrid tempGrid;

    public GameOfLife(int width, int height) {
        this.currentGrid = new ArrayCellGrid(width, height, CellCreator.EMPTY_CELL_CREATOR);
        this.tempGrid = new ArrayCellGrid(width, height, CellCreator.EMPTY_CELL_CREATOR);
    }

    public void init() {
    }

    public void evolve() {
        currentGrid.forEach((x, y) -> {
            Cell cell = currentGrid.getCell(x, y).clone();
            cell.evolve(currentGrid.getNeighbours(x, y));
            tempGrid.setCell(cell, x, y);
        });

        var temp = tempGrid;
        tempGrid = currentGrid;
        currentGrid = temp;
    }

    public CellGrid grid() {
        return currentGrid;
    }
}
