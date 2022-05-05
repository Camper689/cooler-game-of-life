package com.cgol.coolergameoflife.cell.context;

import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.cell.state.CellState;

import java.util.HashMap;
import java.util.Map;

public class CellContext {

    private final Cell[] neighbours;
    private final Map<Integer, Short> cache = new HashMap<>();

    public CellContext(Cell[] neighbours) {
        this.neighbours = neighbours;
    }

    public short countNeighbours(int tag) {
        return cache.computeIfAbsent(
                tag, (key) -> {
                    short count = 0;
                    for (Cell neighbour : neighbours) {
                        if (neighbour.tag() == key) {
                            count++;
                        }
                    }

                    return count;
                }
        );
    }
}
