package com.cgol.coolergameoflife;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.grid.ArrayCellGrid;
import com.cgol.coolergameoflife.grid.CellGrid;

public class GameOfLife {

    private final CellGrid currentGrid;
    private final GameOfLifeConfiguration configuration;

    public GameOfLife(int width, int height, GameOfLifeConfiguration configuration) {
        this.configuration = configuration;

        this.currentGrid = new ArrayCellGrid(width, height, configuration::generateDefaultCell);
    }

    public void evolve() {
        currentGrid.forEach((x, y) -> {
            CellContext context = new CellContext(currentGrid.getNeighbours(x, y));
            currentGrid.getCell(x, y).calculateNextState(context, configuration);
        });

        currentGrid.forEach((x, y) -> {
            currentGrid.getCell(x, y).evolve();
        });
    }

    public CellGrid grid() {
        return currentGrid;
    }

    public GameOfLifeConfiguration configuration() {
        return configuration;
    }
}
