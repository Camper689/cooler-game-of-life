package com.cgol.grid;

import com.cgol.cell.Cell;
import com.cgol.utils.CellGridForEachHandler;
import org.jetbrains.annotations.NotNull;

public interface CellGrid {

    int getWidth();

    int getHeight();

    @NotNull Cell getCell(int x, int y);

    void setCell(@NotNull Cell cell, int x, int y);

    void forEach(CellGridForEachHandler handler);

    Cell[] getNeighbours(int x, int y);
}
