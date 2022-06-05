package com.cgol.coolergameoflife.utils;

import com.cgol.coolergameoflife.cell.CellState;
import com.cgol.coolergameoflife.cell.CellStateTransition;
import com.cgol.coolergameoflife.condition.ConditionReader;

import java.util.List;

public final class DefaultCellStates {

    private static final String EMPTY_CELL_NAME = "Empty cell";
    private static final String POPULATED_CELL_NAME = "Populated cell";

    public static final CellState DEFAULT_EMPTY_CELL_STATE = new CellState(
            EMPTY_CELL_NAME,
            List.of(
                    new CellStateTransition(
                            POPULATED_CELL_NAME,
                            ConditionReader.DEFAULT_CONDITION_READER.readCondition("count(\"" + POPULATED_CELL_NAME + "\") == 3")
                    )
            )
    );

    public static final CellState DEFAULT_POPULATED_CELL_STATE = new CellState(
            POPULATED_CELL_NAME,
            List.of(
                    new CellStateTransition(
                            EMPTY_CELL_NAME,
                            ConditionReader.DEFAULT_CONDITION_READER.readCondition("count(\"" + POPULATED_CELL_NAME + "\") < 2")
                    ),
                    new CellStateTransition(
                            EMPTY_CELL_NAME,
                            ConditionReader.DEFAULT_CONDITION_READER.readCondition("count(\"" + POPULATED_CELL_NAME + "\") >= 4")
                    )
            )
    );

    private DefaultCellStates() {
    }
}
