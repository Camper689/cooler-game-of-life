package com.cgol.cell;

import com.cgol.utils.Behaviors;

public class EmptyCell extends AbstractCell {

    public EmptyCell(int x, int y) {
        super(Behaviors.EMPTY_CELL, x, y);
    }
}
