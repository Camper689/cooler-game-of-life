package com.cgol.coolergameoflife.cell.state;

import com.cgol.coolergameoflife.cell.CellContext;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CellState {

    private final String tag;
    private final List<CellStateTransition> transitions;

    public CellState(String tag, List<CellStateTransition> transitions) {
        this.tag = tag;
        this.transitions = transitions;
    }

    public @Nullable String evolve(CellContext context) {
        for (CellStateTransition transition : transitions) {
            if (transition.predicate().check(context)) {
                return transition.newStateTag();
            }
        }

        return this.tag;
    }

    public String tag() {
        return tag;
    }

    public List<CellStateTransition> transitions() {
        return transitions;
    }
}
