package com.cgol.coolergameoflife;

import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.cell.CellState;
import com.cgol.coolergameoflife.cell.CellStateTransition;
import com.cgol.coolergameoflife.utils.DefaultCellStates;
import com.cgol.exception.BadConfigurationException;
import com.cgol.utils.TrimStringDeserializer;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameOfLifeConfiguration {

    private final List<CellState> states = new ArrayList<>();

    @JsonDeserialize(using = TrimStringDeserializer.class)
    private final String defaultStateName;

    public static final GameOfLifeConfiguration DEFAULT_CONFIGURATION = new GameOfLifeConfiguration(
            DefaultCellStates.DEFAULT_EMPTY_CELL_STATE.name(),
            DefaultCellStates.DEFAULT_EMPTY_CELL_STATE,
            DefaultCellStates.DEFAULT_POPULATED_CELL_STATE
    );

    public GameOfLifeConfiguration(String defaultStateName, CellState... states) {
        this.defaultStateName = defaultStateName;

        for (CellState state : states) {
            this.addState(state);
        }
    }

    public Cell generateDefaultCell() {
        return new Cell(getState(defaultStateName));
    }

    public void addState(CellState state) {
        states.add(state);
    }

    public CellState getState(String name) {
        for (CellState state : states) {
            if(state.name().equals(name))
                return state;
        }

        throw new IllegalArgumentException("Unknown cell state name: " + name);
    }

    public CellState getState(int index) {
        return states.get(index);
    }

    @JsonGetter
    public List<CellState> states() {
        return states;
    }

    @JsonGetter
    public String defaultStateName() {
        return defaultStateName;
    }

    public void validate() {
        throwOnCondition(this.defaultStateName == null || this.defaultStateName.isEmpty(), "Default state cannot be null");
        throwOnCondition(this.states.isEmpty(), "State list cannot be empty");

        Set<String> stateNames = this.states.stream().map(CellState::name).collect(Collectors.toSet());
        throwOnCondition(stateNames.size() != states.size(), "There is duplicate names in configuration");
        throwOnCondition(!stateNames.contains(this.defaultStateName), "Unknown state name " + defaultStateName);
        throwOnCondition(stateNames.contains(null) || stateNames.contains(""), "Empty state names are not allowed");

        List<CellStateTransition> transitions = states.stream().flatMap(state -> state.transitions().stream()).collect(Collectors.toList());
        for (CellStateTransition transition : transitions) {
            throwOnCondition(!stateNames.contains(transition.newStateName()), "Unknown transition into state " + transition.newStateName());
        }
    }

    private void throwOnCondition(boolean condition, String errorMessage) {
        if(condition)
            throw new BadConfigurationException(errorMessage);
    }
}
