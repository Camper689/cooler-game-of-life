package com.game_of_cells.gameofcells.condition.token;

import org.springframework.expression.ExpressionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenReader {

    private final String fullRegex;
    private final String regex;
    private final Token.Type type;

    public TokenReader(String regex, Token.Type type) {
        this.regex = "^" + regex;
        this.fullRegex = this.regex + ".*";

        this.type = type;
    }

    public boolean matches(String string) {
        return string.matches(fullRegex);
    }

    public Token read(String string) {
        Matcher matcher = Pattern.compile(regex).matcher(string);
        if (!matcher.find()) {
            throw new ExpressionException("Cannot find string by regex " + this.regex);
        }

        String group = matcher.group();
        return new Token(group, type);
    }
}
