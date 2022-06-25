package com.game_of_cells.gameofcells.condition.value;

import com.game_of_cells.gameofcells.cell.CellContext;

public interface Value<Type> {

    Type calculate(CellContext context);

    String asString();
}
