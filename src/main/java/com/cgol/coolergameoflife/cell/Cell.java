package com.cgol.coolergameoflife.cell;

import com.cgol.coolergameoflife.cell.behavior.CellBehavior;

public interface Cell extends Cloneable {

    void evolve(Cell[] neighbours);

    CellBehavior getBehavior();

    void setBehavior(CellBehavior behavior);

    int getTag();

    Cell clone();
}
