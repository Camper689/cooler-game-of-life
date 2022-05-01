package com.cgol.coolergameoflife;

import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.cell.EmptyCell;
import com.cgol.coolergameoflife.grid.ArrayCellGrid;
import com.cgol.coolergameoflife.grid.CellGrid;

public class GameOfLife {

    private CellGrid currentGrid;
    private CellGrid tempGrid;

    public GameOfLife(int width, int height) {
        this.currentGrid = new ArrayCellGrid(width, height, EmptyCell::new);
        this.tempGrid = new ArrayCellGrid(width, height, EmptyCell::new);
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
