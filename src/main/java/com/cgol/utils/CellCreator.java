package com.cgol.utils;

import com.cgol.cell.Cell;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface CellCreator {

    @NotNull Cell create(int x, int y);
}
