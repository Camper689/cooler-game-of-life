package com.cgol.utils;

import com.cgol.cell.behavior.CellBehavior;
import com.cgol.cell.behavior.EmptyCellBehavior;
import com.cgol.cell.behavior.PopulatedCellBehavior;

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
