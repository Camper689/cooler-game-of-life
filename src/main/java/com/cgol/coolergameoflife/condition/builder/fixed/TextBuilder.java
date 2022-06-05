package com.cgol.coolergameoflife.condition.builder.fixed;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.condition.ConditionReader;
import com.cgol.coolergameoflife.condition.builder.ValueBuilder;
import com.cgol.coolergameoflife.condition.token.Token;
import com.cgol.coolergameoflife.condition.value.Text;

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
