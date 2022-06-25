package com.game_of_cells.gameofcells.condition.builder.fixed;

import com.game_of_cells.gameofcells.cell.CellContext;
import com.game_of_cells.gameofcells.condition.ConditionReader;
import com.game_of_cells.gameofcells.condition.builder.ValueBuilder;
import com.game_of_cells.gameofcells.condition.token.Token;
import com.game_of_cells.gameofcells.condition.value.Condition;

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
