package com.cgol.controller;

import com.cgol.dto.ConfigurationDto;
import com.cgol.dto.GridDto;
import com.cgol.dto.GridSizeDto;
import com.cgol.service.GameOfLifeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/add/{x}/{y}")
    public void addCell(@PathVariable("x") int x, @PathVariable("y") int y) {
        gameOfLifeService.addCell(x, y);
    }

    @PostMapping("/new-game")
    public void newGame(@RequestBody GridSizeDto gridSizeDto) {
        gameOfLifeService.newGame(gridSizeDto.getWidth(), gridSizeDto.getHeight());
    }

    @PostMapping("/evolve")
    public GridDto evolve() {
        return gameOfLifeService.evolve();
    }

    @GetMapping("/configuration")
    public ConfigurationDto getConfiguration() {
        return gameOfLifeService.getConfiguration();
    }
}
