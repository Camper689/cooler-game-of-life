package com.game_of_cells.controller;

import com.game_of_cells.gameofcells.GameOfLifeConfiguration;
import com.game_of_cells.dto.GridDto;
import com.game_of_cells.dto.NewGameData;
import com.game_of_cells.exception.BadConfigurationException;
import com.game_of_cells.service.GameOfLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
public class GameOfLifeController {

    @Autowired
    protected GameOfLifeService gameOfLifeService;

    @GetMapping("/grid")
    public GridDto getGrid() {
        return gameOfLifeService.getGrid(sessionId());
    }

    @PutMapping("/add/{x}/{y}/{name}")
    public void addCell(@PathVariable("x") int x, @PathVariable("y") int y, @PathVariable("name") String cellStateName) {
        gameOfLifeService.addCell(sessionId(), x, y, cellStateName);
    }

    @PostMapping("/new-game")
    public ResponseEntity<?> newGame(@RequestBody NewGameData newGameData) {
        try {
            gameOfLifeService.newGame(
                    sessionId(),
                    newGameData.getGrid().getWidth(),
                    newGameData.getGrid().getHeight(),
                    newGameData.getConfiguration()
            );

            return ResponseEntity.ok().build();
        } catch (BadConfigurationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/evolve")
    public GridDto evolve() {
        return gameOfLifeService.evolve(sessionId());
    }

    @GetMapping("/configuration")
    public GameOfLifeConfiguration getConfiguration() {
        return gameOfLifeService.getConfiguration(sessionId());
    }

    private String sessionId() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}
