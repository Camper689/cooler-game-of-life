package com.cgol.coolergameoflife.condition.builder.fixed;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.condition.ConditionReader;
import com.cgol.coolergameoflife.condition.builder.ValueBuilder;
import com.cgol.coolergameoflife.condition.token.Token;
import com.cgol.coolergameoflife.condition.value.Number;
import com.cgol.coolergameoflife.condition.value.Value;

import java.util.List;

public class NumberBuilder implements ValueBuilder<Number> {

    @Override
    public boolean canBuild(Token token) {
        return token.getType() == Token.Type.OPERAND_NUMBER;
    }

    @Override
    public Number buildValue(Token token, ConditionReader.ProcessedValues previousValues) {
        int number = Integer.parseInt(token.getText());
        return new Number() {
            @Override
            public Integer calculate(CellContext context) {
                return number;
            }

            @Override
            public String asString() {
                return String.valueOf(number);
            }
        };
    }
}
