package com.cgol.coolergameoflife;

import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.grid.ArrayCellGrid;
import com.cgol.coolergameoflife.grid.CellGrid;

public class GameOfLife {

    private CellGrid currentGrid;
    private CellGrid tempGrid;
    private final GameOfLifeConfiguration configuration;

    public GameOfLife(int width, int height, GameOfLifeConfiguration configuration) {
        this.configuration = configuration;

        this.currentGrid = new ArrayCellGrid(width, height, configuration::generateDefaultCell);
        this.tempGrid = new ArrayCellGrid(width, height, configuration::generateDefaultCell);
    }

    public void evolve() {
        currentGrid.forEach((x, y) -> {
            Cell cell = currentGrid.getCell(x, y).clone();
            CellContext context = new CellContext(currentGrid.getNeighbours(x, y));
            cell.evolve(context, configuration);

            tempGrid.setCell(cell, x, y);
        });

        var temp = tempGrid;
        tempGrid = currentGrid;
        currentGrid = temp;
    }

    public CellGrid grid() {
        return currentGrid;
    }

    public GameOfLifeConfiguration configuration() {
        return configuration;
    }
}
