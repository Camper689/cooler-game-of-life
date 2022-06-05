package com.cgol.coolergameoflife.cell;

import java.util.HashMap;
import java.util.Map;

public class CellContext {

    private final Cell[] neighbours;
    private final Map<String, Integer> cache = new HashMap<>();

    public CellContext(Cell[] neighbours) {
        this.neighbours = neighbours;
    }

    public int countNeighbours(String name) {
        return cache.computeIfAbsent(
                name, (key) -> {
                    int count = 0;
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
