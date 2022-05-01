package com.cgol.controller;

import com.cgol.dto.GridSize;
import com.cgol.service.GameOfLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameOfLifeController {

    @Autowired
    private GameOfLifeService gameOfLifeService;

    @GetMapping("/grid")
    public List<List<Integer>> getGrid() {
        return gameOfLifeService.getGrid();
    }

    @PutMapping("/add/{x}/{y}")
    public void addCell(@PathVariable("x") int x, @PathVariable("y") int y) {
        gameOfLifeService.addCell(x, y);
    }

    @PostMapping("/new-game")
    public void newGame(@RequestBody GridSize gridSize) {
        gameOfLifeService.newGame(gridSize.getWidth(), gridSize.getHeight());
    }

    @PostMapping("/evolve")
    public void evolve() {
        gameOfLifeService.evolve();
    }
}
