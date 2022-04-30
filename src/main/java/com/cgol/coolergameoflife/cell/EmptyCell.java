package com.cgol.coolergameoflife.cell;

import com.cgol.coolergameoflife.utils.Behaviors;

public class EmptyCell extends AbstractCell {

    public EmptyCell(int x, int y) {
        super(Behaviors.EMPTY_CELL, x, y);
    }
}
