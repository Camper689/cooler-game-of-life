package com.game_of_cells.gameofcells.condition.token;

import org.springframework.expression.ExpressionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.game_of_cells.gameofcells.condition.token.Token.Type.FUNCTION;
import static com.game_of_cells.gameofcells.condition.token.Token.Type.FUNCTION_START;
import static com.game_of_cells.gameofcells.condition.token.Token.Type.OPEN_FUNCTION;

public class Tokenizer {

    private final List<TokenReader> readers = new ArrayList<>();

    private List<Token> unsortedTokens;
    private List<Token> sortedTokens;
    private Stack<Token> stack;

    public Tokenizer(Iterable<TokenReader> readers) {
        readers.forEach(this.readers::add);
    }

    public List<Token> readTokens(String string) {
        unsortedTokens = new ArrayList<>();
        sortedTokens = new ArrayList<>();
        stack = new Stack<>();

        do {
            string = string.trim();
            if (string.isEmpty()) {
                break;
            }

            Token token = readNextToken(string);
            unsortedTokens.add(token);
            string = string.substring(token.getText().length());
        }
        while (true);

        for (Token unsortedToken : unsortedTokens) {
            processToken(unsortedToken);
        }

        while (!stack.isEmpty()) {
            sortedTokens.add(stack.pop());
        }

        return sortedTokens;
    }

    private void processToken(Token token) {
        switch (token.getType()) {
            case OPERAND_NUMBER:
            case OPERAND_BOOLEAN:
            case OPERAND_STRING:
                sortedTokens.add(token);
                return;
            case FUNCTION_PARAM_SPLIT:
                while (!stack.isEmpty() && stack.peek().getType() != OPEN_FUNCTION) {
                    sortedTokens.add(stack.pop());
                }

                if (stack.isEmpty()) {
                    throw new ExpressionException("Missing comma or opening bracket");
                }
                return;
            case OPERATOR_HIGH:
            case OPERATOR_LOW:
                while (!stack.isEmpty() && stack.peek().isOperator() && stack.peek().getPriority() >= token.getPriority()) {
                    sortedTokens.add(stack.pop());
                }

            case OPEN_FUNCTION:
            case FUNCTION:
                if (token.getType() == FUNCTION) {
                    sortedTokens.add(new Token("(", FUNCTION_START));
                }

                stack.push(token);
                return;
            case CLOSE_FUNCTION:
                while (!stack.isEmpty() && stack.peek().getType() != OPEN_FUNCTION) {
                    sortedTokens.add(stack.pop());
                }

                if (stack.isEmpty()) {
                    throw new ExpressionException("Missing open bracket '('");
                }
                stack.pop();
                if (!stack.isEmpty() && stack.peek().getType() == FUNCTION) {
                    sortedTokens.add(stack.pop());
                }

                return;
        }
    }

    private Token readNextToken(String string) {
        for (TokenReader reader : readers) {
            if (reader.matches(string)) {
                return reader.read(string);
            }
        }

        throw new ExpressionException("Unknown token: " + string);
    }
}
