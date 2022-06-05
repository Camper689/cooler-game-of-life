package com.cgol.coolergameoflife.condition.builder.logical;

import com.cgol.coolergameoflife.condition.ConditionReader;
import com.cgol.coolergameoflife.condition.builder.ValueBuilder;
import com.cgol.coolergameoflife.condition.token.Token;
import com.cgol.coolergameoflife.condition.value.Condition;

import java.util.List;

public abstract class LogicalOperatorBuilder implements ValueBuilder<Condition> {

    protected final String operator;

    protected LogicalOperatorBuilder(String operator) {
        this.operator = operator;
    }

    @Override
    public boolean canBuild(Token token) {
        return token.isOperator() && token.getText().equals(operator);
    }

    @Override
    public Condition buildValue(Token token, ConditionReader.ProcessedValues previousValues) {
        List<Condition> lastTwoConditions = previousValues.getAndRemoveLastTwoConditions();
        return buildValue(lastTwoConditions.get(0), lastTwoConditions.get(1));
    }

    protected abstract Condition buildValue(Condition first, Condition second);
}
