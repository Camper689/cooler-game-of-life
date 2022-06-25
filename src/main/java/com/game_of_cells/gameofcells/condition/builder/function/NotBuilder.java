package com.game_of_cells.gameofcells.condition.builder.function;

import com.game_of_cells.gameofcells.cell.CellContext;
import com.game_of_cells.gameofcells.condition.value.Condition;
import com.game_of_cells.gameofcells.condition.value.Value;

import java.util.List;

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
