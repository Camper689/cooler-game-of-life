package com.cgol.coolergameoflife.condition.builder.comparison;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.condition.value.Condition;
import com.cgol.coolergameoflife.condition.value.Number;

public class MoreBuilder extends ComparisonOperatorBuilder {

    public MoreBuilder(String comparisonSymbol) {
        super(comparisonSymbol);
    }

    @Override
    protected Condition buildValue(Number first, Number second) {
        return new Condition() {
            @Override
            public Boolean calculate(CellContext context) {
                return first.calculate(context) > second.calculate(context);
            }

            @Override
            public String asString() {
                return first.asString() + " " + comparisonSymbol + " " + second.asString();
            }
        };
    }
}
