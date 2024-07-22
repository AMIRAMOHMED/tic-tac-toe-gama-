package com.example.tictactoegama.models;

public class PlayBoard {

    private final char[][] board;
    private int counter;

    public PlayBoard() {
        counter = 0;
        board = new char[][]{{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};
    }

    public char[][] getBoard() {
        return board;
    }

    public void play(int x, int y, char symbol) {
        board[x][y] = symbol;
        counter++;
    }

    public int checkWin(int x, int y, char symbol) {
        int col = 0, row = 0, diag = 0, rdiag = 0;
        int win = -1;
        if (counter == 9) {
            win = 0; // Draw
        }
        if (counter > 4) {
            for (int i = 0; i < 3; i++) {
                if (board[x][i] == symbol) {
                    row++;
                }
                if (board[i][y] == symbol) {
                    col++;
                }
                if (board[i][i] == symbol) {
                    diag++;
                }
                if (board[i][2 - i] == symbol) {
                    rdiag++;
                }
            }
            if (row == 3 || col == 3 || diag == 3 || rdiag == 3) {
                win = 1; // Win
            }
        }
        return win;

    }


}