package com.game_of_cells.gameofcells.condition.builder.comparison;

import com.game_of_cells.gameofcells.condition.ConditionReader;
import com.game_of_cells.gameofcells.condition.builder.ValueBuilder;
import com.game_of_cells.gameofcells.condition.token.Token;
import com.game_of_cells.gameofcells.condition.value.Condition;
import com.game_of_cells.gameofcells.condition.value.Number;

import java.util.List;

public abstract class ComparisonOperatorBuilder implements ValueBuilder<Condition> {

    protected final String comparisonSymbol;

    protected ComparisonOperatorBuilder(String comparisonSymbol) {
        this.comparisonSymbol = comparisonSymbol;
    }

    @Override
    public boolean canBuild(Token token) {
        return token.isOperator() && token.getText().equals(comparisonSymbol);
    }

    @Override
    public Condition buildValue(Token token, ConditionReader.ProcessedValues previousValues) {
        List<Number> lastTwoNumbers = previousValues.getAndRemoveLastTwoNumbers();
        return buildValue(lastTwoNumbers.get(0), lastTwoNumbers.get(1));
    }

    protected abstract Condition buildValue(Number first, Number second);
}
