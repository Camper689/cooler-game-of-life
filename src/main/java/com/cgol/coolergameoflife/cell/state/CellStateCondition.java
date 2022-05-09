package com.cgol.coolergameoflife.cell.state;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.utils.Difference;

public class CellStateCondition {

    private final String stateTag;
    private final Difference difference;
    private final int number;

    public CellStateCondition(String stateTag, Difference difference, int number) {
        this.stateTag = stateTag;
        this.difference = difference;
        this.number = number;
    }

    public final boolean check(CellContext context) {
        short neighbours = context.countNeighbours(stateTag);
        return difference.calculate(neighbours, number);
    }

    public int number() {
        return number;
    }

    public Difference difference() {
        return difference;
    }

    public String stateTag() {
        return stateTag;
    }
}
