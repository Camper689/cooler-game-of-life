package com.cgol.coolergameoflife.condition.builder.function;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.condition.value.Number;
import com.cgol.coolergameoflife.condition.value.Value;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomBuilder extends FunctionBuilder {

    public RandomBuilder(String functionName) {
        super(functionName, 1);
    }

    @Override
    protected Number buildValue(List<Value<?>> params) {
        return new Number() {
            private final Number max = (Number) params.get(0);
            private final Random randomGenerator = new java.util.Random();

            @Override
            public Integer calculate(CellContext context) {
                return randomGenerator.nextInt(max.calculate(context));
            }

            @Override
            public String asString() {
                return functionName + "(" + max.asString() + ")";
            }
        };
    }
}
