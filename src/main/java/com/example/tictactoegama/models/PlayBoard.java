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

        /**
     * @param x row Position
     * @param y Column Position
     * @param symbol Player Symbol
     * @return -1 if none win, 0 if it was a draw, 1 if the last player win
     * @since Add the Symbol to a position then checks if that symbol is in win
     * condition
     */
    public int play(int x, int y, char symbol) {
        int col = 0, row = 0, diag = 0, rdiag = 0;
        board[x][y] = symbol;
        counter++;
        int win = -1;
        if (counter == 9) {
            win = 0; // Draw
        }
        if (counter > 4) {
            for (int i = 0; i < 3; i++) {
                if (board[x][i] == symbol) row++;
                if (board[i][y] == symbol) col++;
                if (board[i][i] == symbol) diag++;
                if (board[i][2 - i] == symbol) rdiag++;

            }
            if (row == 3 || col == 3 || diag == 3 || rdiag == 3)  win = 1; // Win
           
        }
        return win;
    }


}