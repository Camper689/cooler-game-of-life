package com.game_of_cells.gameofcells.condition.builder.comparison;

import com.game_of_cells.gameofcells.cell.CellContext;
import com.game_of_cells.gameofcells.condition.value.Condition;
import com.game_of_cells.gameofcells.condition.value.Number;

public class LessOrEqualBuilder extends ComparisonOperatorBuilder {

    public LessOrEqualBuilder(String comparisonSymbol) {
        super(comparisonSymbol);
    }

    @Override
    protected Condition buildValue(Number first, Number second) {
        return new Condition() {
            @Override
            public Boolean calculate(CellContext context) {
                return first.calculate(context) <= second.calculate(context);
            }

            @Override
            public String asString() {
                return first.asString() + " " + comparisonSymbol + " " + second.asString();
            }
        };
    }
}
