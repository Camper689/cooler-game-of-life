package com.cgol.cell;

import com.cgol.cell.behavior.CellBehavior;

public abstract class AbstractCell implements Cell {

    protected CellBehavior cellBehavior;

    private int x;
    private int y;

    protected AbstractCell(CellBehavior cellBehavior, int x, int y) {
        this.cellBehavior = cellBehavior;

        this.x = x;
        this.y = y;
    }

    protected void setCellBehavior(CellBehavior newCellBehavior) {
        this.cellBehavior = newCellBehavior;
    }

    @Override
    public void evolve(Cell[] neighbours) {
        this.cellBehavior.evolve(this, neighbours);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
}
