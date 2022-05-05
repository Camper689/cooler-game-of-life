package com.cgol.coolergameoflife;

import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.cell.context.CellContext;
import com.cgol.coolergameoflife.grid.ArrayCellGrid;
import com.cgol.coolergameoflife.grid.CellGrid;
import com.cgol.coolergameoflife.utils.CellStates;

import java.util.function.Supplier;

public class GameOfLife {

    private static final Supplier<Cell> EMPTY_CELL_SUPPLIER = () -> new Cell(CellStates.DEFAULT_EMPTY_CELL_BEHAVIOR);

    private CellGrid currentGrid;
    private CellGrid tempGrid;
    private final GameOfLifeConfiguration configuration = new GameOfLifeConfiguration();

    public GameOfLife(int width, int height) {
        this.currentGrid = new ArrayCellGrid(width, height, EMPTY_CELL_SUPPLIER);
        this.tempGrid = new ArrayCellGrid(width, height, EMPTY_CELL_SUPPLIER);

        this.configuration.addState(CellStates.DEFAULT_EMPTY_CELL_BEHAVIOR);
        this.configuration.addState(CellStates.DEFAULT_POPULATED_CELL_BEHAVIOR);
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
