package com.game_of_cells.gameofcells.condition.builder;

import com.game_of_cells.gameofcells.condition.ConditionReader;
import com.game_of_cells.gameofcells.condition.token.Token;
import com.game_of_cells.gameofcells.condition.value.Value;

public interface ValueBuilder<Type extends Value<?>> {

    boolean canBuild(Token token);

    Type buildValue(Token token, ConditionReader.ProcessedValues previousValues);

    ValueBuilder<?> EMPTY_BUILDER = new ValueBuilder<>() {
        @Override
        public boolean canBuild(Token token) {
            return false;
        }

        @Override
        public Value<?> buildValue(Token token, ConditionReader.ProcessedValues previousValues) {
            throw new IllegalStateException("Cannot build value from token type " + token.getType());
        }
    };
}
