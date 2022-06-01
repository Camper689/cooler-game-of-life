package com.cgol.dto;

import com.cgol.coolergameoflife.GameOfLifeConfiguration;
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
