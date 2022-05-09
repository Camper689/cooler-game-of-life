package com.cgol.coolergameoflife.cell;

import com.cgol.coolergameoflife.GameOfLifeConfiguration;
import com.cgol.coolergameoflife.cell.state.CellState;

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
        String newTag = state.evolve(context);
        if (newTag != null) {
            this.state = configuration.getState(newTag);
            return true;
        }

        return false;
    }

    public String tag() {
        return state.tag();
    }
}
