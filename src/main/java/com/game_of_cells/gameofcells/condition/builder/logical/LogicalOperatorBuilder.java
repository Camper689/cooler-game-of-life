package com.game_of_cells.gameofcells.condition.builder.logical;

import com.game_of_cells.gameofcells.condition.ConditionReader;
import com.game_of_cells.gameofcells.condition.builder.ValueBuilder;
import com.game_of_cells.gameofcells.condition.token.Token;
import com.game_of_cells.gameofcells.condition.value.Condition;

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
