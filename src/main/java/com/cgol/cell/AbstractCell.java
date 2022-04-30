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

    @Override
    public Cell clone() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void evolve(Cell[] neighbours) {
        this.cellBehavior.evolve(this, neighbours);
    }

    @Override
    public CellBehavior getBehavior() {
        return cellBehavior;
    }

    @Override
    public int getTag() {
        return cellBehavior.getTag();
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
    public void setBehavior(CellBehavior newCellBehavior) {
        this.cellBehavior = newCellBehavior;
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
