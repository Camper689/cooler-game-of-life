package com.game_of_cells.gameofcells.utils;

import com.game_of_cells.gameofcells.cell.CellState;
import com.game_of_cells.gameofcells.cell.CellStateTransition;
import com.game_of_cells.gameofcells.condition.ConditionReader;

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
