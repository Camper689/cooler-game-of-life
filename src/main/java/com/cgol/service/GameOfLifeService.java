package com.cgol.service;

import com.cgol.coolergameoflife.GameOfLife;
import com.cgol.coolergameoflife.GameOfLifeConfiguration;
import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.grid.CellGrid;
import com.cgol.dto.ConfigurationDto;
import com.cgol.dto.GridDto;
import org.springframework.stereotype.Service;

@Service
public class GameOfLifeService {

    private GameOfLife game = new GameOfLife(
            100, 100,
            GameOfLifeConfiguration.DEFAULT_CONFIGURATION
    );

    public void newGame(int width, int height) {
        game = new GameOfLife(width, height, GameOfLifeConfiguration.DEFAULT_CONFIGURATION);
    }

    public GridDto evolve() {
        game.evolve();
        return getGrid();
    }

    public void addCell(int x, int y) {
        CellGrid grid = game.grid();
        GameOfLifeConfiguration configuration = game.configuration();

        Cell cell = new Cell(configuration.getState("Populated cell"));
        grid.setCell(cell, x, y);
    }

    // TODO: optimize
    public GridDto getGrid() {
        CellGrid cellGrid = game.grid();
        return new GridDto(cellGrid);
    }

    public ConfigurationDto getConfiguration() {
        return new ConfigurationDto(game.configuration());
    }
}
