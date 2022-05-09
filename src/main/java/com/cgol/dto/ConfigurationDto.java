package com.cgol.dto;

import com.cgol.coolergameoflife.GameOfLifeConfiguration;
import com.cgol.coolergameoflife.cell.state.CellState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ConfigurationDto {

    private String defaultTag;
    private List<StateDto> states = new ArrayList<>();

    public ConfigurationDto(GameOfLifeConfiguration configuration) {
        this.setDefaultTag(configuration.defaultTag());
        for (CellState state : configuration.allStates()) {
            this.states.add(new StateDto(state));
        }
    }
}
