package com.cgol.coolergameoflife.cell.state;

import com.cgol.coolergameoflife.cell.context.CellContext;

import java.util.List;

public class CellState {

    private final int tag;
    private final List<CellStateTransition> transitions;

    public CellState(int tag, List<CellStateTransition> transitions) {
        this.tag = tag;
        this.transitions = transitions;
    }

    public int evolve(CellContext context) {
        for (CellStateTransition transition : transitions) {
            if (transition.getPredicate().check(context)) {
                return transition.getNewStateTag();
            }
        }

        return this.tag;
    }

    public int tag() {
        return tag;
    }
}
