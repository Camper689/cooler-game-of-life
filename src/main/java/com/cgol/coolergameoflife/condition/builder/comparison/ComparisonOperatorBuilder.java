package com.cgol.coolergameoflife.condition.builder.comparison;

import com.cgol.coolergameoflife.condition.ConditionReader;
import com.cgol.coolergameoflife.condition.builder.ValueBuilder;
import com.cgol.coolergameoflife.condition.token.Token;
import com.cgol.coolergameoflife.condition.value.Condition;
import com.cgol.coolergameoflife.condition.value.Number;

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
