package com.cgol.cell;

public interface Cell {

    void evolve(Cell[] neighbours);

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);
}
