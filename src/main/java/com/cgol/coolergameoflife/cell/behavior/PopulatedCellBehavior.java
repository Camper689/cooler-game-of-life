package com.cgol.coolergameoflife.cell.behavior;

import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.utils.Behaviors;

public class PopulatedCellBehavior implements CellBehavior {

    @Override
    public void evolve(Cell cell, Cell[] neighbours) {
        int count = 0;
        for (Cell neighbour : neighbours) {
            count += neighbour.getTag();
        }

        if(count >= 5 || count < 2) {
            cell.setBehavior(Behaviors.EMPTY_CELL);
        }
    }

    @Override
    public int getTag() {
        return Behaviors.Tags.POPULATED_CELL;
    }
}
