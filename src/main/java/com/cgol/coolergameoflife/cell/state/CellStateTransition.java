package com.cgol.coolergameoflife.cell.state;

public class CellStateTransition {

    private final CellStateCondition condition;
    private final String newStateTag;

    public CellStateTransition(CellStateCondition condition, String newStateTag) {
        this.condition = condition;
        this.newStateTag = newStateTag;
    }

    public CellStateCondition predicate() {
        return condition;
    }

    public String newStateTag() {
        return newStateTag;
    }
}
