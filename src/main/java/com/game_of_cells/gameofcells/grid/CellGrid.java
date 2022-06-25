package com.game_of_cells.gameofcells.grid;

import com.game_of_cells.gameofcells.cell.Cell;
import com.game_of_cells.gameofcells.utils.CellGridForEachHandler;
import org.jetbrains.annotations.NotNull;

public interface CellGrid {

    int getWidth();

    int getHeight();

    @NotNull Cell getCell(int x, int y);

    void setCell(@NotNull Cell cell, int x, int y);

    void forEach(CellGridForEachHandler handler);

    Cell[] getNeighbours(int x, int y);
}
