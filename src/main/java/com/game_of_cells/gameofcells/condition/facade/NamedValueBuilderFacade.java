package com.game_of_cells.gameofcells.condition.facade;

import com.game_of_cells.gameofcells.condition.builder.ValueBuilder;
import com.game_of_cells.gameofcells.condition.token.Token;
import com.game_of_cells.gameofcells.condition.token.TokenReader;

import java.util.function.Function;

public class NamedValueBuilderFacade extends ValueBuilderFacade {

    public NamedValueBuilderFacade(String functionName, Token.Type tokenType, Function<String, ValueBuilder<?>> creatorFunction) {
        super(
                new TokenReader(functionName, tokenType),
                creatorFunction.apply(functionName)
        );
    }
}
