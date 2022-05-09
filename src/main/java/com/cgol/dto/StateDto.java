package com.cgol.dto;

import com.cgol.coolergameoflife.cell.state.CellState;
import com.cgol.coolergameoflife.cell.state.CellStateTransition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StateDto {

    private String tag;
    private List<TransitionDto> transitions = new ArrayList<>();

    public StateDto(CellState state) {
        this.tag = state.tag();
        for (CellStateTransition transition : state.transitions()) {
            this.transitions.add(new TransitionDto(transition));
        }
    }
}
