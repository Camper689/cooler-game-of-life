package com.game_of_cells.dto;

import com.game_of_cells.gameofcells.GameOfLifeConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewGameData {

    private GridSize grid;
    private GameOfLifeConfiguration configuration;
}
