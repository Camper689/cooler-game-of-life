package com.cgol.controller;

import com.cgol.coolergameoflife.GameOfLifeConfiguration;
import com.cgol.dto.NewGameData;
import com.cgol.dto.GridDto;
import com.cgol.exception.BadConfigurationException;
import com.cgol.service.GameOfLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameOfLifeController {

    @Autowired
    private GameOfLifeService gameOfLifeService;

    @GetMapping("/grid")
    public GridDto getGrid() {
        return gameOfLifeService.getGrid();
    }

    @PutMapping("/add/{x}/{y}/{name}")
    public void addCell(@PathVariable("x") int x, @PathVariable("y") int y, @PathVariable("name") String cellStateName) {
        gameOfLifeService.addCell(x, y, cellStateName);
    }

    @PostMapping("/new-game")
    public ResponseEntity<?> newGame(@RequestBody NewGameData newGameData) {
        try {
            gameOfLifeService.newGame(
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
        return gameOfLifeService.evolve();
    }

    @GetMapping("/configuration")
    public GameOfLifeConfiguration getConfiguration() {
        return gameOfLifeService.getConfiguration();
    }
}
