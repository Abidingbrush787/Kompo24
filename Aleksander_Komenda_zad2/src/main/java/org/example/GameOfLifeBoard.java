package org.example;

import java.util.Arrays;

public class GameOfLifeBoard {
    private boolean[][] board;

    public GameOfLifeBoard(int width, int height) {
        board = new boolean[width][height];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Math.random() < 0.5;
            }
        }
    }

    public void doStep() {
        boolean[][] newBoard = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int aliveNeighbors = countAliveNeighbors(i, j);
                if (board[i][j]) {
                    newBoard[i][j] = aliveNeighbors == 2 || aliveNeighbors == 3;
                } else {
                    newBoard[i][j] = aliveNeighbors == 3;
                }
            }
        }
        board = newBoard;
    }


    private int countAliveNeighbors(int x, int y) {
        int aliveCount = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // Pomijamy komórkę (0,0)
                }

                int nx = x + i;
                int ny = y + j;

                if (nx < 0) {
                    nx = board.length - 1;
                } else if (nx >= board.length) {
                    nx = 0;
                }

                if (ny < 0) {
                    ny = board[0].length - 1;
                } else if (ny >= board[0].length) {
                    ny = 0;
                }

                //System.out.println("Checking wrapped neighbor at (" + nx + ", " + ny + "), alive: " + board[nx][ny]);

                if (board[nx][ny]) {
                    aliveCount++;
                }
            }
        }

        //System.out.println("Alive neighbors for cell (" + x + ", " + y + "): " + aliveCount);
        return aliveCount;
    }


    public void setBoard(boolean[][] newBoard) {
        if (newBoard.length != board.length || newBoard[0].length != board[0].length)
        {
            throw new IllegalArgumentException("New board dimensions must match the current board dimensions.");
        }
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(newBoard[i], 0, board[i], 0, newBoard[i].length);
        }
    }

    // Metoda pomocnicza do wyświetlania planszy
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // Wyświetlamy 'O' dla żywej komórki, '.' dla martwej
                System.out.print(board[i][j] ? "O " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }


    // getBoard: Tylko do testów, może zwracać kopię planszy
    public boolean[][] getBoard() {
        return Arrays.stream(board).map(boolean[]::clone).toArray(boolean[][]::new);
    }
}

