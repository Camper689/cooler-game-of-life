package com.cgol.coolergameoflife.utils;

import com.cgol.coolergameoflife.cell.state.CellState;
import com.cgol.coolergameoflife.cell.state.CellStatePredicate;
import com.cgol.coolergameoflife.cell.state.CellStateTransition;

import java.util.List;

public final class CellStates {

    private static final int EMPTY_CELL_TAG = 0;
    private static final int POPULATED_CELL_TAG = 1;

    public static final CellState DEFAULT_EMPTY_CELL_BEHAVIOR = new CellState(
            EMPTY_CELL_TAG,
            List.of(
                    new CellStateTransition(
                            new CellStatePredicate(
                                    3,
                                    Difference.EQUALS,
                                    POPULATED_CELL_TAG
                            ),
                            POPULATED_CELL_TAG
                    )
            )
    );

    public static final CellState DEFAULT_POPULATED_CELL_BEHAVIOR = new CellState(
            POPULATED_CELL_TAG,
            List.of(
                    new CellStateTransition(
                            new CellStatePredicate(
                                    2,
                                    Difference.MORE,
                                    POPULATED_CELL_TAG
                            ),
                            EMPTY_CELL_TAG
                    ),
                    new CellStateTransition(
                            new CellStatePredicate(
                                    4,
                                    Difference.LESS_OR_EQUAL,
                                    POPULATED_CELL_TAG
                            ),
                            EMPTY_CELL_TAG
                    )
            )
    );

    private CellStates() {
    }
}
