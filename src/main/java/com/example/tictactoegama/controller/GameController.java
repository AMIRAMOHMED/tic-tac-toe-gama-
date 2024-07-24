package com.example.tictactoegama.controller;

import com.example.tictactoegama.interfaces.AIMoodOption;
import com.example.tictactoegama.logic.MediumMood;
import com.example.tictactoegama.models.PlayBoard;
import com.example.tictactoegama.views.SymbolSelectionDialog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.shape.Line;


public class GameController {

    @FXML
    private Text gameStatus;

    @FXML
    private GridPane gameGrid;
    @FXML
    private String currentPlayer;
    private String computerSymbol;
    private PlayBoard playBoard;
    private boolean gameEnded;
    private AIMoodOption aiMoodOption;
    @FXML
    private Line winnerLine;
    private Label difficultyLabel;

    @FXML
    public void initialize() {
        Platform.runLater(this::showSymbolSelectionDialog);
        playBoard = new PlayBoard();
        gameEnded = false;

        if (aiMoodOption != null) {
            difficultyLabel.setText("Difficulty: " + aiMoodOption.getClass().getSimpleName());
        }
    }

    public void setAiMoodOption(AIMoodOption aiMoodOption) {
        this.aiMoodOption = aiMoodOption;
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
        if (gameEnded) {  disableButtons();}

        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().isEmpty()) {
            int row = GridPane.getRowIndex(clickedButton);
            int col = GridPane.getColumnIndex(clickedButton);

            processPlayerMove(clickedButton, row, col);

            if (!gameEnded) {
                PauseTransition pause = new PauseTransition(Duration.seconds(.2));
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
                    Button button = (Button) getNodeByRowColumnIndex(i, j);
                    if (button != null) {
                        button.setText(String.valueOf(symbol));
                        updateButtonStyle(button, String.valueOf(symbol));
                    }
                }
            }
        }
    }



    private void endGame(String winner) {
        String message = "Player " + winner + " wins!";
        gameStatus.setText(message);
        disableButtons();
        gameEnded = true;
        drawWinnerLine(playBoard.getWinningTiles());
        winnerLine.setVisible(true);
    }

    private void disableButtons() {
        for (Node node : gameGrid.getChildren()) {
            if (node instanceof Button) {
                node.setDisable(true);
            }
        }
    }


    private void drawWinnerLine(int[][] winningTiles) {
        if (winningTiles == null || winningTiles.length == 0) return;

        // Get the starting and ending nodes of the winning line
        Node startNode = getNodeByRowColumnIndex(winningTiles[0][0], winningTiles[0][1]);
        Node endNode = getNodeByRowColumnIndex(winningTiles[2][0], winningTiles[2][1]);

        if (startNode != null && endNode != null) {
            // Get the position of the GridPane
            Bounds gridPaneBounds = gameGrid.localToScene(gameGrid.getBoundsInLocal());

            // Calculate the center points of the start and end nodes relative to the GridPane
            double startX = startNode.getBoundsInParent().getMinX() + startNode.getBoundsInParent().getWidth() / 2;
            double startY = startNode.getBoundsInParent().getMinY() + startNode.getBoundsInParent().getHeight() / 2;
            double endX = endNode.getBoundsInParent().getMinX() + endNode.getBoundsInParent().getWidth() / 2;
            double endY = endNode.getBoundsInParent().getMinY() + endNode.getBoundsInParent().getHeight() / 2;

            // Adjust the winnerLine coordinates relative to the GridPane
            winnerLine.setStartX(gridPaneBounds.getMinX() + startX);
            winnerLine.setStartY(gridPaneBounds.getMinY() + startY);
            winnerLine.setEndX(gridPaneBounds.getMinX() + endX);
            winnerLine.setEndY(gridPaneBounds.getMinY() + endY);
            winnerLine.setVisible(true);
        }
    }


    private Node getNodeByRowColumnIndex(final int row, final int column) {
        for (Node node : gameGrid.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }
        return null;
    }

}










