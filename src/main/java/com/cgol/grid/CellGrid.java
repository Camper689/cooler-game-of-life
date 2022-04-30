package com.cgol.grid;

import com.cgol.cell.Cell;
import com.cgol.utils.CellGridForEachHandler;

public interface CellGrid {

    int getWidth();

    int getHeight();

    Cell getCell(int x, int y);

    void setCell(Cell cell, int x, int y);

    void forEach(CellGridForEachHandler handler);

    Cell[] getNeighbours(int x, int y);
}
