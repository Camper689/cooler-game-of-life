package com.cgol.coolergameoflife.grid;

import com.cgol.coolergameoflife.utils.CellGridForEachHandler;

public abstract class AbstractCellGrid implements CellGrid {

    protected final int width;
    protected final int height;

    protected AbstractCellGrid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void forEach(CellGridForEachHandler handler) {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                handler.handle(x, y);
            }
        }
    }
}
