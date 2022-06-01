package com.cgol.coolergameoflife.cell.state;

import com.cgol.utils.TrimStringDeserializer;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CellStateTransition {

    @JsonDeserialize(using = TrimStringDeserializer.class)
    private final String newStateName;
    private final CellStateCondition condition;

    public CellStateTransition(CellStateCondition condition, String newStateName) {
        this.condition = condition;
        this.newStateName = newStateName;
    }

    @JsonGetter
    public CellStateCondition condition() {
        return condition;
    }

    @JsonGetter
    public String newStateName() {
        return newStateName;
    }
}
