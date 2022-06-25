package com.game_of_cells.gameofcells.condition.builder.fixed;

import com.game_of_cells.gameofcells.cell.CellContext;
import com.game_of_cells.gameofcells.condition.ConditionReader;
import com.game_of_cells.gameofcells.condition.builder.ValueBuilder;
import com.game_of_cells.gameofcells.condition.token.Token;
import com.game_of_cells.gameofcells.condition.value.Text;

public class TextBuilder implements ValueBuilder<Text> {

    @Override
    public boolean canBuild(Token token) {
        return token.getType() == Token.Type.OPERAND_STRING;
    }

    @Override
    public Text buildValue(Token token, ConditionReader.ProcessedValues previousValues) {
        String text = token.getText().substring(0, token.getText().length() - 1).substring(1);
        return new Text() {
            @Override
            public String calculate(CellContext context) {
                return text;
            }

            @Override
            public String asString() {
                return '\"' + text + '\"';
            }
        };
    }
}
