package com.cgol.coolergameoflife.cell.behavior;

import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.utils.Behaviors;

public class EmptyCellBehavior implements CellBehavior {

    @Override
    public void evolve(Cell cell, Cell[] neighbours) {
        int count = 0;
        for (Cell neighbour : neighbours) {
            count += neighbour.getTag();
        }

        if(count == 3) {
            cell.setBehavior(Behaviors.POPULATED_CELL);
        }
    }

    @Override
    public int getTag() {
        return Behaviors.Tags.EMPTY_CELL;
    }
}
