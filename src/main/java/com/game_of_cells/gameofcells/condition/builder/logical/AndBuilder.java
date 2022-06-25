package com.game_of_cells.gameofcells.condition.builder.logical;

import com.game_of_cells.gameofcells.cell.CellContext;
import com.game_of_cells.gameofcells.condition.value.Condition;

public class AndBuilder extends LogicalOperatorBuilder {

    public AndBuilder(String operator) {
        super(operator);
    }

    @Override
    protected Condition buildValue(Condition first, Condition second) {
        return new Condition() {
            @Override
            public Boolean calculate(CellContext context) {
                return first.calculate(context) && second.calculate(context);
            }

            @Override
            public String asString() {
                return first.asString() + " " + operator + " " + second.asString();
            }
        };
    }
}
