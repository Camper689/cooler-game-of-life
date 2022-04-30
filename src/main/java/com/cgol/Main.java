package com.cgol;

import com.cgol.game.ConsoleGameOfLife;

public class Main {

    public static void main(String[] args) {
        ConsoleGameOfLife gameOfLife = new ConsoleGameOfLife(30, 10);

        gameOfLife.evolve();
        gameOfLife.evolve();
        gameOfLife.evolve();
        gameOfLife.evolve();

        gameOfLife.display();
        System.out.println();
    }
}