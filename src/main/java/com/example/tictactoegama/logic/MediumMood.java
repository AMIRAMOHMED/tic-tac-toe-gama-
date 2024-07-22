package com.example.tictactoegama.logic;

import com.example.tictactoegama.models.PlayBoard;
import com.example.tictactoegama.interfaces.AIMoodOption;

import java.util.Random;

public class MediumMood implements AIMoodOption {

    public void makeMove(PlayBoard board, char computerSymbol) {
        Random rand = new Random(System.currentTimeMillis());
        int row, col;
        char[][] currentBoard = board.getBoard();
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (currentBoard[row][col] != '_');
        board.play(row, col, computerSymbol);
    }
}
