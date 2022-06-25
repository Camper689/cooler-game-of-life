package com.game_of_cells.gameofcells.condition.builder.function;

import com.game_of_cells.gameofcells.cell.CellContext;
import com.game_of_cells.gameofcells.condition.value.Number;
import com.game_of_cells.gameofcells.condition.value.Value;

import java.util.List;

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
