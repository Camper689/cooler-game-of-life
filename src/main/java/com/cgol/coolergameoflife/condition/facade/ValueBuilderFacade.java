package com.cgol.coolergameoflife.condition.facade;

import com.cgol.coolergameoflife.condition.builder.ValueBuilder;
import com.cgol.coolergameoflife.condition.token.TokenReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class ValueBuilderFacade {

    private final TokenReader tokenReader;
    private final ValueBuilder<?> valueBuilder;
}
