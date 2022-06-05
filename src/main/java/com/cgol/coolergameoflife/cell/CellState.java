package com.cgol.coolergameoflife.cell;

import com.cgol.utils.TrimStringDeserializer;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CellState {

    @JsonDeserialize(using = TrimStringDeserializer.class)
    private final String name;
    private final List<CellStateTransition> transitions;

    public CellState(String name, List<CellStateTransition> transitions) {
        this.name = name;
        this.transitions = transitions;
    }

    public @Nullable String evolve(CellContext context) {
        for (CellStateTransition transition : transitions) {
            if (transition.check(context)) {
                return transition.newStateName();
            }
        }

        return null;
    }

    @JsonGetter
    public String name() {
        return name;
    }

    @JsonGetter
    public List<CellStateTransition> transitions() {
        return transitions;
    }
}
