package com.cgol.coolergameoflife.cell;

import java.util.HashMap;
import java.util.Map;

public class CellContext {

    private final Cell[] neighbours;
    private final Map<String, Short> cache = new HashMap<>();

    public CellContext(Cell[] neighbours) {
        this.neighbours = neighbours;
    }

    public short countNeighbours(String name) {
        return cache.computeIfAbsent(
                name, (key) -> {
                    short count = 0;
                    for (Cell neighbour : neighbours) {
                        if (neighbour.name().equals(key)) {
                            count++;
                        }
                    }

                    return count;
                }
        );
    }
}
