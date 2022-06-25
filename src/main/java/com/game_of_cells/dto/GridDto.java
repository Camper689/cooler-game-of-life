package com.game_of_cells.dto;

import com.game_of_cells.gameofcells.cell.CellState;
import com.game_of_cells.gameofcells.grid.CellGrid;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GridDto {

    private final Map<String, Integer> replaces = new HashMap<>();
    // TODO: optimize
    private final List<List<Integer>> grid = new ArrayList<>();

    public GridDto(Collection<CellState> states, CellGrid cellGrid) {
        int index = 0;
        for (CellState state : states) {
            replaces.put(state.name(), index++);
        }

        for (int y = 0; y < cellGrid.getHeight(); y++) {
            List<Integer> currentList = new ArrayList<>();
            grid.add(currentList);

            for (int x = 0; x < cellGrid.getWidth(); x++) {
                String tag = cellGrid.getCell(x, y).name();
                int tagReplace = replaces.get(tag);

                currentList.add(tagReplace);
            }
        }
    }
}
