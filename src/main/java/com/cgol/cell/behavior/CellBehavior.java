package com.cgol.cell.behavior;

import com.cgol.cell.Cell;

public interface CellBehavior {

    default void evolve(Cell cell, Cell[] neighbours) {

    }
}
