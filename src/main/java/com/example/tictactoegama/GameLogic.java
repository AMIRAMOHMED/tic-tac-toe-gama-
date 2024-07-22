package com.example.tictactoegama;

import javafx.scene.control.Button;

public class GameLogic {

    private final PlayBoard playBoard;
    private final AIMoodOption aiMoodOption;

    public GameLogic(PlayBoard playBoard, AIMoodOption aiMoodOption) {
        this.playBoard = playBoard;
        this.aiMoodOption = aiMoodOption;
    }

    public void processPlayerMove(Button clickedButton, int row, int col, String currentPlayer) {
        clickedButton.setText(currentPlayer);
        updateButtonStyle(clickedButton, currentPlayer);
        playBoard.play(row, col, currentPlayer.charAt(0));
    }

    public void processComputerMove(String computerSymbol) {
        aiMoodOption.makeMove(playBoard, computerSymbol.charAt(0));
    }

    public boolean checkForWin(int row, int col, char symbol) {
        return playBoard.checkWin(row, col, symbol) != -1;
    }

    public boolean checkForWinAfterComputerMove(String computerSymbol) {
        char[][] board = playBoard.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == computerSymbol.charAt(0)) {
                    if (checkForWin(i, j, computerSymbol.charAt(0))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void updateButtonStyle(Button button, String symbol) {
    }
}
