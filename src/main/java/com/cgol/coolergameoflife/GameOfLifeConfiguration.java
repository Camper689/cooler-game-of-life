package com.cgol.coolergameoflife;

import com.cgol.coolergameoflife.cell.state.CellState;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameOfLifeConfiguration {

    private final Map<Integer, CellState> stateMap = new HashMap<>();

    public GameOfLifeConfiguration() {
    }

    public void addState(CellState state) {
        stateMap.put(state.tag(), state);
    }

    public CellState getState(int tag) {
        return Objects.requireNonNull(stateMap.get(tag));
    }
}
