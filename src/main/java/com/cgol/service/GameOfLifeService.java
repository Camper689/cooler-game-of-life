package com.cgol.service;

import com.cgol.coolergameoflife.GameOfLife;
import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.cell.PopulatedCell;
import com.cgol.coolergameoflife.grid.CellGrid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameOfLifeService {

    private GameOfLife game = new GameOfLife(100, 100);

    public void newGame(int width, int height) {
        game = new GameOfLife(width, height);
    }

    public List<List<Integer>> getGrid() {
        if (game == null) {
            return null;
        }

        CellGrid cellGrid = game.grid();

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentList;

        for (int y = 0; y < cellGrid.getHeight(); y++) {
            currentList = new ArrayList<>();
            for (int x = 0; x < cellGrid.getWidth(); x++) {
                currentList.add(cellGrid.getCell(x, y).getTag());
            }
            result.add(currentList);
        }

        return result;
    }

    public void evolve() {
        if (game != null) {
            game.evolve();
        }
    }

    public void addCell(int x, int y) {
        if (game == null) {
            return;
        }

        CellGrid grid = game.grid();
        Cell cell = grid.getCell(x, y);
        if (cell.getTag() == 0) {
            grid.setCell(new PopulatedCell(), x, y);
        }
    }
}
