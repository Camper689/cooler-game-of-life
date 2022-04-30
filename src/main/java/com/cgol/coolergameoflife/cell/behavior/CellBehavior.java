package com.cgol.coolergameoflife.cell.behavior;

import com.cgol.coolergameoflife.cell.Cell;

public interface CellBehavior {

    default void evolve(Cell cell, Cell[] neighbours) {

    }

    int getTag();
}
