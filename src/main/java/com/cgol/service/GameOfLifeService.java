package com.cgol.service;

import com.cgol.coolergameoflife.GameOfLife;
import com.cgol.coolergameoflife.GameOfLifeConfiguration;
import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.grid.CellGrid;
import com.cgol.dto.GridDto;
import org.springframework.stereotype.Service;

@Service
public class GameOfLifeService {

    private GameOfLife game = new GameOfLife(100, 100, GameOfLifeConfiguration.DEFAULT_CONFIGURATION);

    public void newGame(int width, int height, GameOfLifeConfiguration configuration) {
        configuration.validate();
        game = new GameOfLife(width, height, configuration);
    }

    public GridDto evolve() {
        game.evolve();
        return getGrid();
    }

    public void addCell(int x, int y, String name) {
        CellGrid grid = game.grid();
        GameOfLifeConfiguration configuration = game.configuration();

        Cell cell = grid.getCell(x, y);
        cell.evolveInto(configuration.getState(name));
    }

    // TODO: optimize
    public GridDto getGrid() {
        return new GridDto(game.configuration().states(), game.grid());
    }

    public GameOfLifeConfiguration getConfiguration() {
        return game.configuration();
    }
}
