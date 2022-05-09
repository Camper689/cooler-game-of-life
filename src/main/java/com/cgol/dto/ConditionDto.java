package com.cgol.dto;

import com.cgol.coolergameoflife.cell.state.CellStateCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConditionDto {

    private String stateTag;
    private String symbol;
    private int number;

    public ConditionDto(CellStateCondition predicate) {
        this.number = predicate.number();
        this.symbol = predicate.difference().symbol();
        this.stateTag = predicate.stateTag();
    }
}
