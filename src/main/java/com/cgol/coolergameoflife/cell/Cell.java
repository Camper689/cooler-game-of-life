package com.cgol.coolergameoflife.cell;

import com.cgol.coolergameoflife.cell.context.CellContext;
import com.cgol.coolergameoflife.cell.state.CellState;
import com.cgol.coolergameoflife.GameOfLifeConfiguration;

public class Cell implements Cloneable {

    protected CellState state;

    public Cell(CellState state) {
        this.state = state;
    }

    public Cell clone() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean evolve(CellContext context, GameOfLifeConfiguration configuration) {
        int newTag = state.evolve(context);
        if(newTag != tag()) {
            this.state = configuration.getState(newTag);
            return true;
        }

        return false;
    }

    public CellState behavior() {
        return state;
    }

    public int tag() {
        return state.tag();
    }

    public void behavior(CellState newCellState) {
        this.state = newCellState;
    }
}
