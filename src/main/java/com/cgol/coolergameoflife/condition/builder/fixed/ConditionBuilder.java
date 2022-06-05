package com.cgol.coolergameoflife.condition.builder.fixed;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.condition.ConditionReader;
import com.cgol.coolergameoflife.condition.builder.ValueBuilder;
import com.cgol.coolergameoflife.condition.token.Token;
import com.cgol.coolergameoflife.condition.value.Condition;

public class ConditionBuilder implements ValueBuilder<Condition> {

    @Override
    public boolean canBuild(Token token) {
        return token.getType() == Token.Type.OPERAND_BOOLEAN;
    }

    @Override
    public Condition buildValue(Token token, ConditionReader.ProcessedValues previousValues) {
        boolean value = Boolean.parseBoolean(token.getText());
        return new Condition() {
            @Override
            public Boolean calculate(CellContext context) {
                return value;
            }

            @Override
            public String asString() {
                return String.valueOf(value);
            }
        };
    }
}
