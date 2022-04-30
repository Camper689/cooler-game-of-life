package com.cgol.utils;

import com.cgol.cell.behavior.CellBehavior;
import com.cgol.cell.behavior.EmptyCellBehavior;
import com.cgol.cell.behavior.PopulatedCellBehavior;

public final class Behaviors {

    public static final CellBehavior EMPTY_CELL = new EmptyCellBehavior();
    public static final CellBehavior POPULATED_CELL = new PopulatedCellBehavior();

    private Behaviors() {}
}
