package com.cgol.coolergameoflife.utils;

import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.cell.EmptyCell;
import com.cgol.coolergameoflife.cell.PopulatedCell;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@FunctionalInterface
public interface CellCreator {

    CellCreator EMPTY_CELL_CREATOR = EmptyCell::new;
    CellCreator RANDOM_CELL_CREATOR = (x, y) -> {
        if(new Random().nextBoolean()) {
            return new PopulatedCell(x, y);
        } else {
            return new EmptyCell(x, y);
        }
    };

    @NotNull Cell create(int x, int y);
}
