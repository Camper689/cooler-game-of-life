package com.cgol.coolergameoflife.utils;

import org.jetbrains.annotations.Nullable;

public enum Difference {

    EQUALS("=", (x, y) -> x == y),
    MORE(">", (x, y) -> x > y),
    LESS("<", (x, y) -> x < y),
    MORE_OR_EQUAL(">=", (x, y) -> x >= y),
    LESS_OR_EQUAL("<=", (x, y) -> x <= y);

    private final String symbol;
    private final CompareNumbersFunction function;

    Difference(String symbol, CompareNumbersFunction function) {
        this.symbol = symbol;
        this.function = function;
    }

    public static @Nullable Difference getFromSymbol(String symbol) {
        for (Difference value : values()) {
            if(value.symbol.equals(symbol))
                return value;
        }

        return null;
    }

    public String symbol() {
        return symbol;
    }

    public boolean calculate(int x, int y) {
        return function.calculate(x, y);
    }

    @FunctionalInterface
    private interface CompareNumbersFunction {
        boolean calculate(int x, int y);
    }
}
