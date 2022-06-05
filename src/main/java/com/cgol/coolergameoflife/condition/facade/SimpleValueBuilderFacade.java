package com.cgol.coolergameoflife.condition.facade;

import com.cgol.coolergameoflife.condition.builder.ValueBuilder;
import com.cgol.coolergameoflife.condition.token.TokenReader;

public class SimpleValueBuilderFacade extends ValueBuilderFacade {

    public SimpleValueBuilderFacade(TokenReader tokenReader, ValueBuilder<?> valueBuilder) {
        super(tokenReader, valueBuilder);
    }
}
