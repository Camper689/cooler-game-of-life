package com.cgol.coolergameoflife.condition.value;

import com.cgol.coolergameoflife.cell.CellContext;

public interface Value<Type> {

    Type calculate(CellContext context);

    String asString();
}
