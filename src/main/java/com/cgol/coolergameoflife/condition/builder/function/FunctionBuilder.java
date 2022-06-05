package com.cgol.coolergameoflife.condition.builder.function;

import com.cgol.coolergameoflife.condition.ConditionReader;
import com.cgol.coolergameoflife.condition.builder.ValueBuilder;
import com.cgol.coolergameoflife.condition.token.Token;
import com.cgol.coolergameoflife.condition.value.Value;

import java.util.List;

public abstract class FunctionBuilder implements ValueBuilder<Value<?>> {

    protected final String functionName;
    protected final int expectedNumberOfParams;

    protected FunctionBuilder(String functionName, int expectedNumberOfParams) {
        this.functionName = functionName;
        this.expectedNumberOfParams = expectedNumberOfParams;
    }

    protected FunctionBuilder(String functionName) {
        this.functionName = functionName;
        this.expectedNumberOfParams = -1;
    }

    @Override
    public final boolean canBuild(Token token) {
        return token.getType() == Token.Type.FUNCTION &&
                functionName.equals(token.getText());
    }

    @Override
    public Value<?> buildValue(Token token, ConditionReader.ProcessedValues previousValues) {
        List<Value<?>> params = previousValues.getAndRemoveFunctionParams(expectedNumberOfParams);
        return buildValue(params);
    }

    protected abstract Value<?> buildValue(List<Value<?>> params);
}
