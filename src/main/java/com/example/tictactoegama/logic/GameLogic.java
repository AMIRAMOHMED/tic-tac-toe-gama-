package com.example.tictactoegama.logic;

import com.example.tictactoegama.models.PlayBoard;
import com.example.tictactoegama.interfaces.AIMoodOption;
import javafx.scene.control.Button;

public class GameLogic {

    private final PlayBoard playBoard;
    private final AIMoodOption aiMoodOption;

    public GameLogic(PlayBoard playBoard, AIMoodOption aiMoodOption) {
        this.playBoard = playBoard;
        this.aiMoodOption = aiMoodOption;
    }

    public void processPlayerMove(Button clickedButton, int row, int col, char currentPlayer) {
        clickedButton.setText(String.valueOf(currentPlayer));
        updateButtonStyle(clickedButton, String.valueOf(currentPlayer));
        playBoard.play(row, col, currentPlayer);
    }

    public void processComputerMove(char computerSymbol) {
        aiMoodOption.makeMove(playBoard, computerSymbol);
    }

    public boolean checkForWin(int row, int col, char symbol) {
        return playBoard.checkWin(row, col, symbol) != -1;
    }



    private void updateButtonStyle(Button button, String symbol) {
    }
}
