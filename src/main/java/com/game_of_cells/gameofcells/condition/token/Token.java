package com.game_of_cells.gameofcells.condition.token;

import lombok.Getter;

@Getter
public class Token {

    private final String text;
    private final Type type;

    public Token(String text, Type type) {
        this.text = text;
        this.type = type;
    }

    public boolean isOperator() {
        return type == Type.OPERATOR_HIGH || type == Type.OPERATOR_LOW;
    }

    public final int getPriority() {
        return type == Type.OPERATOR_HIGH ? 1 : 0;
    }

    @Override
    public String toString() {
        return type + " (" + text + ')';
    }

    public enum Type {
        OPEN_FUNCTION, CLOSE_FUNCTION,
        OPERATOR_HIGH, OPERATOR_LOW,
        FUNCTION_PARAM_SPLIT, FUNCTION, FUNCTION_START,
        OPERAND_NUMBER, OPERAND_BOOLEAN, OPERAND_STRING;
    }
}
