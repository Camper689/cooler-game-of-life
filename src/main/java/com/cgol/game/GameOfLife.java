package com.cgol.game;

import com.cgol.grid.CellGrid;

public interface GameOfLife {

    void evolve();

    CellGrid grid();
}
