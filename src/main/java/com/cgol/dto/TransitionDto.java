package com.cgol.dto;

import com.cgol.coolergameoflife.cell.state.CellStateTransition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransitionDto {

    private ConditionDto condition;
    private String newStateTag;

    public TransitionDto(CellStateTransition transition) {
        this.condition = new ConditionDto(transition.predicate());
        this.newStateTag = transition.newStateTag();
    }
}
