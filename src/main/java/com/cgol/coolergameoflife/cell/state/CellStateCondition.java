package com.cgol.coolergameoflife.cell.state;

import com.cgol.coolergameoflife.cell.CellContext;
import com.cgol.coolergameoflife.utils.Difference;
import com.cgol.utils.TrimStringDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CellStateCondition {

    @JsonDeserialize(using = TrimStringDeserializer.class)
    private final String neighboursStateName;
    private final Difference difference;
    private final int number;

    @JsonCreator
    public CellStateCondition(String state, String symbol, int number) {
        this(state, Difference.getFromSymbol(symbol), number);
    }

    public CellStateCondition(String neighboursStateName, Difference difference, int number) {
        this.neighboursStateName = neighboursStateName;
        this.difference = difference;
        this.number = number;
    }

    public final boolean check(CellContext context) {
        short neighbours = context.countNeighbours(neighboursStateName);
        return difference.calculate(neighbours, number);
    }

    @JsonGetter
    public String neighboursStateName() {
        return neighboursStateName;
    }

    @JsonGetter
    public String symbol() {
        return difference.symbol();
    }

    @JsonGetter
    public int number() {
        return number;
    }

    public Difference difference() {
        return difference;
    }
}
