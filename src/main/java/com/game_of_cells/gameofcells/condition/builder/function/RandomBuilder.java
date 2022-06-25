package com.game_of_cells.gameofcells.condition.builder.function;

import com.game_of_cells.gameofcells.cell.CellContext;
import com.game_of_cells.gameofcells.condition.value.Number;
import com.game_of_cells.gameofcells.condition.value.Value;

import java.util.List;
import java.util.Random;

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
