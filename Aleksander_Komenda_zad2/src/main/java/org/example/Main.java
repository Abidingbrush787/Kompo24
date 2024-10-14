package org.example;

import org.apache.commons.lang3.StringUtils;

public class Main {
    public static void main(String[] args) {

        GameOfLifeBoard board = new GameOfLifeBoard(8, 8);

        // Początkowy stan planszy
        System.out.println("Initial Board:");
        board.printBoard();

        for (int i = 0; i < 8; i++) { //Dowolna ilosc iteracji
            board.doStep();
            System.out.println("Board after step " + (i + 1) + ":");
            board.printBoard();
        }
    }

}

