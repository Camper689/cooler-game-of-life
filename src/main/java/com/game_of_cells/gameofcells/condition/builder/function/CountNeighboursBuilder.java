package com.game_of_cells.gameofcells.condition.builder.function;

import com.game_of_cells.gameofcells.cell.CellContext;
import com.game_of_cells.gameofcells.condition.value.Number;
import com.game_of_cells.gameofcells.condition.value.Text;
import com.game_of_cells.gameofcells.condition.value.Value;

import java.util.List;
import java.util.stream.Collectors;

public class CountNeighboursBuilder extends FunctionBuilder {

    public CountNeighboursBuilder(String functionName) {
        super(functionName, 1);
    }

    @Override
    protected Number buildValue(List<Value<?>> params) {
        return new Number() {
            private final Text text = (Text) params.get(0);

            @Override
            public Integer calculate(CellContext context) {
                return context.countNeighbours(text.calculate(context));
            }

            @Override
            public String asString() {
                return functionName + "(" + params.stream().map(Value::asString).collect(Collectors.joining(", ")) + ")";
            }
        };
    }
}
