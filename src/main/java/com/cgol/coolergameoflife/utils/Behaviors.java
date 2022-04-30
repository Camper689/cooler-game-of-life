package com.cgol.coolergameoflife.utils;

import com.cgol.coolergameoflife.cell.behavior.CellBehavior;
import com.cgol.coolergameoflife.cell.behavior.EmptyCellBehavior;
import com.cgol.coolergameoflife.cell.behavior.PopulatedCellBehavior;

public final class Behaviors {

    public static final CellBehavior EMPTY_CELL = new EmptyCellBehavior();
    public static final CellBehavior POPULATED_CELL = new PopulatedCellBehavior();

    private Behaviors() {}

    public static class Tags {

        public static final int EMPTY_CELL = 0;
        public static final int POPULATED_CELL = 1;

        private Tags() {}
    }
}
