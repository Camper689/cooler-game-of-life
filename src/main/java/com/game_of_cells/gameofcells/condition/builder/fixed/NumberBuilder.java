package com.game_of_cells.gameofcells.condition.builder.fixed;

import com.game_of_cells.gameofcells.cell.CellContext;
import com.game_of_cells.gameofcells.condition.ConditionReader;
import com.game_of_cells.gameofcells.condition.builder.ValueBuilder;
import com.game_of_cells.gameofcells.condition.token.Token;
import com.game_of_cells.gameofcells.condition.value.Number;

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
