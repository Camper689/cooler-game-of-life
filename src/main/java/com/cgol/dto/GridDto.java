package com.cgol.dto;

import com.cgol.coolergameoflife.grid.CellGrid;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class GridDto {

    private final Map<String, Integer> replaces = new HashMap<>();
    private final List<List<Integer>> grid = new ArrayList<>();

    // TODO: optimize
    public GridDto(CellGrid cellGrid) {
        AtomicInteger current = new AtomicInteger();
        for (int y = 0; y < cellGrid.getHeight(); y++) {
            List<Integer> currentList = new ArrayList<>();
            grid.add(currentList);

            for (int x = 0; x < cellGrid.getWidth(); x++) {
                String tag = cellGrid.getCell(x, y).tag();
                int tagReplace = replaces.computeIfAbsent(tag, key -> current.getAndIncrement());

                currentList.add(tagReplace);
            }
        }
    }
}
