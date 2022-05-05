package com.cgol.coolergameoflife.cell.state;

import com.cgol.coolergameoflife.cell.context.CellContext;
import com.cgol.coolergameoflife.utils.Difference;

public class CellStatePredicate {

    private final int number;
    private final Difference difference;
    private final int stateTag;

    public CellStatePredicate(int number, Difference difference, int stateTag) {
        this.number = number;
        this.difference = difference;
        this.stateTag = stateTag;
    }

    public final boolean check(CellContext context) {
        short neighbours = context.countNeighbours(stateTag);
        return difference.calculate(number, neighbours);
    }
}
