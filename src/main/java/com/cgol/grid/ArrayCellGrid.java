package com.cgol.grid;

import com.cgol.cell.Cell;
import com.cgol.utils.CellCreator;
import org.jetbrains.annotations.NotNull;

public class ArrayCellGrid extends AbstractCellGrid {

    private final Cell[][] grid;

    public ArrayCellGrid(int width, int height, @NotNull CellCreator cellCreator) {
        super(width, height);

        this.grid = new Cell[width][height];

        forEach((x, y) -> {
            Cell cell = cellCreator.create(x, y);
            setCell(cell, x, y);
        });

    }

    @Override
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    @Override
    public void setCell(Cell cell, int x, int y) {
        grid[x][y] = cell;
    }

    @Override
    public Cell[] getNeighbours(int x, int y) {
        return new Cell[0];
    }
}
