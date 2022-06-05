package com.cgol.coolergameoflife.condition.builder;

import com.cgol.coolergameoflife.condition.ConditionReader;
import com.cgol.coolergameoflife.condition.token.Token;
import com.cgol.coolergameoflife.condition.value.Value;

import java.util.List;

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
