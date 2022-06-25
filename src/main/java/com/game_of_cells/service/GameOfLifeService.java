package com.game_of_cells.service;

import com.game_of_cells.gameofcells.GameOfLife;
import com.game_of_cells.gameofcells.GameOfLifeConfiguration;
import com.game_of_cells.gameofcells.cell.Cell;
import com.game_of_cells.gameofcells.grid.CellGrid;
import com.game_of_cells.dto.GridDto;
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
