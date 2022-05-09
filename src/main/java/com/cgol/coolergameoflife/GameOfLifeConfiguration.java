package com.cgol.coolergameoflife;

import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.cell.state.CellState;
import com.cgol.coolergameoflife.utils.CellStates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GameOfLifeConfiguration {

    private final Map<String, CellState> stateMap = new HashMap<>();
    private final String defaultTag;

    public static final GameOfLifeConfiguration DEFAULT_CONFIGURATION = new GameOfLifeConfiguration(
            CellStates.DEFAULT_EMPTY_CELL_BEHAVIOR.tag(),
            CellStates.DEFAULT_EMPTY_CELL_BEHAVIOR,
            CellStates.DEFAULT_POPULATED_CELL_BEHAVIOR
    );

    public GameOfLifeConfiguration(String defaultTag) {
        this.defaultTag = defaultTag;
    }

    public GameOfLifeConfiguration(String defaultTag, CellState... states) {
        this.defaultTag = defaultTag;

        for (CellState state : states) {
            this.addState(state);
        }
    }

    public Cell generateDefaultCell() {
        return new Cell(getState(defaultTag));
    }

    public void addState(CellState state) {
        stateMap.put(state.tag(), state);
    }

    public CellState getState(String tag) {
        return Objects.requireNonNull(stateMap.get(tag));
    }

    public List<CellState> allStates() {
        return new ArrayList<>(stateMap.values());
    }

    public String defaultTag() {
        return defaultTag;
    }
}
