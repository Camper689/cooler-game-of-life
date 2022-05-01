package com.cgol.coolergameoflife.cell;

import com.cgol.coolergameoflife.cell.behavior.CellBehavior;

public abstract class AbstractCell implements Cell {

    protected CellBehavior cellBehavior;

    protected AbstractCell(CellBehavior cellBehavior) {
        this.cellBehavior = cellBehavior;
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
    public void setBehavior(CellBehavior newCellBehavior) {
        this.cellBehavior = newCellBehavior;
    }

}
