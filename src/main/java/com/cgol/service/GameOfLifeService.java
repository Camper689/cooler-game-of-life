package com.cgol.service;

import com.cgol.coolergameoflife.GameOfLife;
import com.cgol.coolergameoflife.GameOfLifeConfiguration;
import com.cgol.coolergameoflife.cell.Cell;
import com.cgol.coolergameoflife.grid.CellGrid;
import com.cgol.dto.GridDto;
import org.springframework.stereotype.Service;

@Service
public class GameOfLifeService {

    private final GameOfLifeSessionsContainer games = new GameOfLifeSessionsContainer();

    public void newGame(String id, int width, int height, GameOfLifeConfiguration configuration) {
        configuration.validate();
        games.put(id, new GameOfLife(width, height, configuration));
    }

    public GridDto evolve(String id) {
        games.get(id).evolve();
        return getGrid(id);
    }

    public void addCell(String id, int x, int y, String name) {
        GameOfLife game = games.get(id);

        CellGrid grid = game.grid();
        GameOfLifeConfiguration configuration = game.configuration();

        Cell cell = grid.getCell(x, y);
        cell.evolveInto(configuration.getState(name));
    }

    // TODO: optimize
    public GridDto getGrid(String id) {
        GameOfLife game = games.get(id);
        return new GridDto(game.configuration().states(), game.grid());
    }

    public GameOfLifeConfiguration getConfiguration(String id) {
        return games.get(id).configuration();
    }
}
