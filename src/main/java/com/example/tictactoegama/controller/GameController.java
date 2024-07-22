package com.example.tictactoegama.controller;

import com.example.tictactoegama.interfaces.AIMoodOption;
import com.example.tictactoegama.logic.MediumMood;
import com.example.tictactoegama.models.PlayBoard;
import com.example.tictactoegama.views.SymbolSelectionDialog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.Node;

public class GameController {

    @FXML
    private Text gameStatus;

    @FXML
    private GridPane gameGrid;

    @FXML
    private Button btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22;

    private String currentPlayer;
    private String computerSymbol;
    private PlayBoard playBoard;
    private boolean gameEnded;
    private AIMoodOption aiMoodOption;

    @FXML
    public void initialize() {
        Platform.runLater(this::showSymbolSelectionDialog);
        playBoard = new PlayBoard();
        gameEnded = false;
        aiMoodOption = new MediumMood();
    }

    private void showSymbolSelectionDialog() {
        SymbolSelectionDialog dialog = new SymbolSelectionDialog(this);
        dialog.show();
    }

    public void handleSymbolSelection(String playerSymbol, String compSymbol) {
        currentPlayer = playerSymbol;
        computerSymbol = compSymbol;
        gameStatus.setText("Current Player: " + currentPlayer);
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        if (gameEnded) return;

        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().isEmpty()) {
            int row = GridPane.getRowIndex(clickedButton);
            int col = GridPane.getColumnIndex(clickedButton);

            processPlayerMove(clickedButton, row, col);

            if (!gameEnded) {
                PauseTransition pause = new PauseTransition(Duration.seconds(.5));
                pause.setOnFinished(e -> processComputerMove());
                pause.play();
            }
        }
    }

    private void processPlayerMove(Button clickedButton, int row, int col) {
        clickedButton.setText(currentPlayer);
        updateButtonStyle(clickedButton, currentPlayer);
        

        if (playBoard.play(row, col, currentPlayer.charAt(0))== 1) {
            endGame(currentPlayer);
        }
    }

    private void processComputerMove() {
        if (aiMoodOption.makeMove(playBoard, computerSymbol.charAt(0))==1) {
            endGame(computerSymbol);
        }
        updateBoardUI();
    }


    private void updateButtonStyle(Button button, String symbol) {
        if ("X".equals(symbol)) {
            button.setStyle(
                    "-fx-background-color: rgba(70, 163, 255, 0.2); " +
                            "-fx-text-fill: #46A3FF; " +
                            "-fx-font-size: 36px; " +
                            "-fx-border-color: #E3E3E3;"
            );
        } else if ("O".equals(symbol)) {
            button.setStyle(
                    "-fx-background-color: rgba(255, 130, 126, 0.2); " +
                            "-fx-text-fill: #FF827E; " +
                            "-fx-font-size: 36px; " +
                            "-fx-border-color: #E3E3E3;"
            );
        }
    }

    private void updateBoardUI() {
        char[][] board = playBoard.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char symbol = board[i][j];
                if (symbol != '_') {
                    Button button = getNodeByRowColumnIndex(i, j);
                    if (button != null) {
                        button.setText(String.valueOf(symbol));
                        updateButtonStyle(button, String.valueOf(symbol));
                    }
                }
            }
        }
    }

    private Button getNodeByRowColumnIndex(final int row, final int column) {
        for (Node node : gameGrid.getChildren()) {
            Integer nodeRow = GridPane.getRowIndex(node);
            Integer nodeCol = GridPane.getColumnIndex(node);
            if (nodeRow != null && nodeCol != null && nodeRow == row && nodeCol == column) {
                return (Button) node;
            }
        }
        return null;
    }

    private void endGame(String winner) {
        String message = "Player " + winner + " wins!";
        gameStatus.setText(message);
        disableButtons();
        gameEnded = true;
    }

    private void disableButtons() {
        for (Node node : gameGrid.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
        }
    }
}
