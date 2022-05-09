package com.cgol.coolergameoflife.utils;

import com.cgol.coolergameoflife.cell.state.CellState;
import com.cgol.coolergameoflife.cell.state.CellStateCondition;
import com.cgol.coolergameoflife.cell.state.CellStateTransition;

import java.util.List;

public final class CellStates {

    private static final String EMPTY_CELL_TAG = "Empty cell";
    private static final String POPULATED_CELL_TAG = "Populated cell";

    public static final CellState DEFAULT_EMPTY_CELL_BEHAVIOR = new CellState(
            EMPTY_CELL_TAG,
            List.of(
                    new CellStateTransition(
                            new CellStateCondition(
                                    POPULATED_CELL_TAG,
                                    Difference.EQUALS,
                                    3
                            ),
                            POPULATED_CELL_TAG
                    )
            )
    );

    public static final CellState DEFAULT_POPULATED_CELL_BEHAVIOR = new CellState(
            POPULATED_CELL_TAG,
            List.of(
                    new CellStateTransition(
                            new CellStateCondition(
                                    POPULATED_CELL_TAG,
                                    Difference.LESS,
                                    2
                            ),
                            EMPTY_CELL_TAG
                    ),
                    new CellStateTransition(
                            new CellStateCondition(
                                    POPULATED_CELL_TAG,
                                    Difference.MORE_OR_EQUAL,
                                    4
                            ),
                            EMPTY_CELL_TAG
                    )
            )
    );

    private CellStates() {
    }
}
