package com.cgol.coolergameoflife.utils;

import com.cgol.coolergameoflife.cell.state.CellState;
import com.cgol.coolergameoflife.cell.state.CellStateCondition;
import com.cgol.coolergameoflife.cell.state.CellStateTransition;

import java.util.List;

public final class DefaultCellStates {

    private static final String EMPTY_CELL_NAME = "Empty cell";
    private static final String POPULATED_CELL_NAME = "Populated cell";

    public static final CellState DEFAULT_EMPTY_CELL_STATE = new CellState(
            EMPTY_CELL_NAME,
            List.of(
                    new CellStateTransition(
                            new CellStateCondition(
                                    POPULATED_CELL_NAME,
                                    Difference.EQUALS,
                                    3
                            ),
                            POPULATED_CELL_NAME
                    )
            )
    );

    public static final CellState DEFAULT_POPULATED_CELL_STATE = new CellState(
            POPULATED_CELL_NAME,
            List.of(
                    new CellStateTransition(
                            new CellStateCondition(
                                    POPULATED_CELL_NAME,
                                    Difference.LESS,
                                    2
                            ),
                            EMPTY_CELL_NAME
                    ),
                    new CellStateTransition(
                            new CellStateCondition(
                                    POPULATED_CELL_NAME,
                                    Difference.MORE_OR_EQUAL,
                                    4
                            ),
                            EMPTY_CELL_NAME
                    )
            )
    );

    private DefaultCellStates() {
    }
}
