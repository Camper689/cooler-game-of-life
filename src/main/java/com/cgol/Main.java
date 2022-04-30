package com.cgol;

import com.cgol.game.GameOfLife;
import com.cgol.grid.CellGrid;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        GameOfLife gameOfLife = new GameOfLife(30, 10);

        while (true) {
            Thread.sleep(1500);

            System.out.println();
            System.out.println("=========================================================================");
            System.out.println();

            gameOfLife.evolve();
            display(gameOfLife);
        }
    }

    private static void display(GameOfLife gameOfLife) {
        final int[] lastY = {0};
        CellGrid grid = gameOfLife.grid();
        grid.forEach((x, y) -> {
            if (y != lastY[0]) {
                System.out.println();
                lastY[0] = y;
            }
            System.out.print(grid.getCell(x, y).getTag());
        });
    }
}