package com.cgol.cell;

import com.cgol.cell.behavior.CellBehavior;

public interface Cell extends Cloneable {

    void evolve(Cell[] neighbours);

    CellBehavior getBehavior();
    int getTag();
    int getX();
    int getY();

    void setBehavior(CellBehavior behavior);
    void setX(int x);
    void setY(int y);

    Cell clone();
}
