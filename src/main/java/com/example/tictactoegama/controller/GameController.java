package com.example.tictactoegama.controller;

import com.example.tictactoegama.interfaces.AIMoodOption;
import com.example.tictactoegama.logic.MediumMood;
import com.example.tictactoegama.models.PlayBoard;
import com.example.tictactoegama.views.SymbolSelectionDialog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.shape.Line;

import java.io.IOException;


public class GameController {



    @FXML
    private Text gameStatus;

    @FXML
    private GridPane gameGrid;
    @FXML
    private String currentPlayer;
    private Scene originalGameScene;
    private  boolean isDraw;
    boolean isLocal;
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
                PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
                pause.setOnFinished(e -> processComputerMove());
                pause.play();
            }
        }
    }

    private void processPlayerMove(Button clickedButton, int row, int col) {
        clickedButton.setText(currentPlayer);
        updateButtonStyle(clickedButton, currentPlayer);
        int flag = playBoard.play(row,col,currentPlayer.charAt(0));

        if (flag== 1) {
            endGame(currentPlayer);
        }
        if (flag== 0) {
            endGame("draw");
        }
    }

    private void processComputerMove() {
        int flag = aiMoodOption.makeMove(playBoard,computerSymbol.charAt(0));
        if (flag==1) {
            endGame(computerSymbol);
        }
        else if (flag==0){
            endGame("draw");
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

        String videoPath = "";


        if (winner.equals(currentPlayer)) {

            videoPath = "/Users/interlink/Downloads/WhatsApp Video 2024-07-23 at 01.37.18.mp4";
        } else if (winner.equals(computerSymbol)) {

            videoPath = "/Users/interlink/Downloads/WhatsApp Video 2024-07-23 at 01.22.37.mp4";
        } else if (winner.equals("draw")) {

            videoPath = "/Users/interlink/Downloads/WhatsApp Video 2024-07-23 at 01.22.46.mp4";
        } else {
            return;
        }


        disableButtons();
        gameEnded = true;
        drawWinnerLine(playBoard.getWinningTiles());
        winnerLine.setVisible(true);

        final String finalVideoPath = videoPath;
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> showVideoView(finalVideoPath));

        delay.play();
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
    private void showVideoView(String videoPath) {
        try {
            // Store the original game scene
            originalGameScene = gameGrid.getScene();

            // Load the video view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tictactoegama/views/Video.fxml"));
            Parent videoRoot = loader.load();
            Stage stage = (Stage) gameGrid.getScene().getWindow();
            Scene videoScene = new Scene(videoRoot);

            // Get the VideoController and set the stage, previous scene, and video path
            VideoController videoController = loader.getController();
            videoController.setStageAndPreviousScene(stage, originalGameScene);
            videoController.setVideoPath(videoPath);

            // Set the video scene
            stage.setScene(videoScene);

            // Pause for 10 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(event -> {
                // Switch back to the original game scene
                stage.setScene(originalGameScene);
            });
            pause.play();

        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }


}










