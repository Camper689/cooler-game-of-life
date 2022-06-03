package com.cgol.service;

import com.cgol.coolergameoflife.GameOfLife;
import com.cgol.coolergameoflife.GameOfLifeConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class GameOfLifeSessionsContainer {

    private final Map<String, GameOfLife> currentGames = new HashMap<>();
    private final Supplier<GameOfLife> defaultGameSupplier = () -> new GameOfLife(100, 100, GameOfLifeConfiguration.DEFAULT_CONFIGURATION);

    public GameOfLife get(String id) {
        GameOfLife gameOfLife = currentGames.get(id);
        if(gameOfLife == null) {
            GameOfLife newGame = defaultGameSupplier.get();
            currentGames.put(id, newGame);

            return newGame;
        }

        return gameOfLife;
    }

    public GameOfLife put(String id, GameOfLife game) {
        return currentGames.put(id, game);
    }
}
