package com.cgol.coolergameoflife.condition.facade;

import com.cgol.coolergameoflife.condition.builder.ValueBuilder;
import com.cgol.coolergameoflife.condition.token.Token;
import com.cgol.coolergameoflife.condition.token.TokenReader;

import java.util.function.Function;

public class NamedValueBuilderFacade extends ValueBuilderFacade {

    public NamedValueBuilderFacade(String functionName, Token.Type tokenType, Function<String, ValueBuilder<?>> creatorFunction) {
        super(
                new TokenReader(functionName, tokenType),
                creatorFunction.apply(functionName)
        );
    }
}
