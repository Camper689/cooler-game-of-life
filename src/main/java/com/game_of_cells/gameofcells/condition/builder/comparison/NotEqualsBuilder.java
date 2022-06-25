package com.game_of_cells.gameofcells.condition.builder.comparison;

import com.game_of_cells.gameofcells.cell.CellContext;
import com.game_of_cells.gameofcells.condition.value.Condition;
import com.game_of_cells.gameofcells.condition.value.Number;

public class NotEqualsBuilder extends ComparisonOperatorBuilder {

    public NotEqualsBuilder(String comparisonSymbol) {
        super(comparisonSymbol);
    }

    @Override
    protected Condition buildValue(Number first, Number second) {
        return new Condition() {
            @Override
            public Boolean calculate(CellContext context) {
                return !first.calculate(context).equals(second.calculate(context));
            }

            @Override
            public String asString() {
                return first.asString() + " " + comparisonSymbol + " " + second.asString();
            }
        };
    }
}
