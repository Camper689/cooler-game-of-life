package com.cgol.coolergameoflife.condition.builder.function;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.condition.value.Number;
import com.cgol.coolergameoflife.condition.value.Value;

import java.util.List;
import java.util.stream.Collectors;

public class DiffBuilder extends FunctionBuilder {

    public DiffBuilder(String functionName) {
        super(functionName, 2);
    }

    @Override
    protected Number buildValue(List<Value<?>> params) {
        return new Number() {
            private final Number first = (Number) params.get(0);
            private final Number second = (Number) params.get(0);

            @Override
            public Integer calculate(CellContext context) {
                return first.calculate(context) - second.calculate(context);
            }

            @Override
            public String asString() {
                return functionName + "(" + first.asString() + ", " + second.asString() + ")";
            }
        };
    }
}
