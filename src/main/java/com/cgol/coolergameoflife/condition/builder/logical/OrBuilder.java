package com.cgol.coolergameoflife.condition.builder.logical;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.condition.value.Condition;

public class OrBuilder extends LogicalOperatorBuilder {

    public OrBuilder(String operator) {
        super(operator);
    }

    @Override
    protected Condition buildValue(Condition first, Condition second) {
        return new Condition() {
            @Override
            public Boolean calculate(CellContext context) {
                return first.calculate(context) || second.calculate(context);
            }

            @Override
            public String asString() {
                return first.asString() + " " + operator + " " + second.asString();
            }
        };
    }
}
