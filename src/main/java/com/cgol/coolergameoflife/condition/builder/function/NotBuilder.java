package com.cgol.coolergameoflife.condition.builder.function;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.condition.value.Condition;
import com.cgol.coolergameoflife.condition.value.Value;

import java.util.List;
import java.util.stream.Collectors;

public class NotBuilder extends FunctionBuilder {

    public NotBuilder(String functionName) {
        super(functionName, 1);
    }

    @Override
    protected Condition buildValue(List<Value<?>> params) {
        return new Condition() {
            private final Condition value = (Condition) params.get(0);

            @Override
            public Boolean calculate(CellContext context) {
                return !value.calculate(context);
            }

            @Override
            public String asString() {
                return functionName + "(" + value.asString() + ")";
            }
        };
    }
}
