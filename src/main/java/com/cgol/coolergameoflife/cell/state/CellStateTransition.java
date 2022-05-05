package com.cgol.coolergameoflife.cell.state;

public class CellStateTransition {

    private final CellStatePredicate predicate;
    private final int newStateTag;

    public CellStateTransition(CellStatePredicate predicate, int newStateTag) {
        this.predicate = predicate;
        this.newStateTag = newStateTag;
    }

    public CellStatePredicate getPredicate() {
        return predicate;
    }

    public int getNewStateTag() {
        return newStateTag;
    }
}
