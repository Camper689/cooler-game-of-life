package com.game_of_cells.gameofcells.condition.facade;

import com.game_of_cells.gameofcells.condition.builder.ValueBuilder;
import com.game_of_cells.gameofcells.condition.token.TokenReader;

public class SimpleValueBuilderFacade extends ValueBuilderFacade {

    public SimpleValueBuilderFacade(TokenReader tokenReader, ValueBuilder<?> valueBuilder) {
        super(tokenReader, valueBuilder);
    }
}
