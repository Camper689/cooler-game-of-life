package com.game_of_cells.gameofcells.condition.facade;

import com.game_of_cells.gameofcells.condition.builder.ValueBuilder;
import com.game_of_cells.gameofcells.condition.token.TokenReader;
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
