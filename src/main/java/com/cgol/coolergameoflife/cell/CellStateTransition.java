package com.cgol.coolergameoflife.cell;

import com.cgol.coolergameoflife.condition.ConditionReader;
import com.cgol.coolergameoflife.condition.value.Condition;
import com.cgol.utils.TrimStringDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CellStateTransition {

    @JsonDeserialize(using = TrimStringDeserializer.class)
    private final String newStateName;
    @JsonIgnore
    private final Condition condition;

    public CellStateTransition(String newStateName, Condition condition) {
        this.newStateName = newStateName;
        this.condition = condition;
    }

    @JsonCreator
    public CellStateTransition(String newStateName, String condition) {
        this.newStateName = newStateName;
        this.condition = ConditionReader.DEFAULT_CONDITION_READER.readCondition(condition);
    }

    @JsonGetter
    public String newStateName() {
        return newStateName;
    }

    @JsonGetter
    public String condition() {
        return condition.asString();
    }

    public boolean check(CellContext context) {
        return condition.calculate(context);
    }
}
