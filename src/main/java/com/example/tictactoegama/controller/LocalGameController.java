package com.example.tictactoegama.controller;

import com.example.tictactoegama.models.PlayBoard;
import com.example.tictactoegama.models.VideoViewHandler;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LocalGameController {

    @FXML
    GridPane gameGrid;
    @FXML
    Label playerXNametxt,playerONametxt , OScoreLabel , XScoreLabel;
    @FXML
    Text gameStatus;
    @FXML
    private Line winnerLine;
    @FXML
    Button replayBtn, gotoHomeBtn;

    private boolean gameEnded;
    private PlayBoard playBoard;
    static int XScore,OScore;
    int numberOfPlayes;
    private VideoViewHandler videoViewHandler;
    static String playerXName,playerOName;
    String currentPlayer;


    @FXML
    public void initialize() {
        playBoard = new PlayBoard();
        gameEnded = false;
        numberOfPlayes=0;
        playerXNametxt.setText(playerXName);
        playerONametxt.setText(playerOName);
        OScoreLabel.setText(""+OScore);
        XScoreLabel.setText(""+XScore);
        videoViewHandler = new VideoViewHandler();
        gameStatus.setText(playerXName+"'s Turn");
    }
    @FXML
    public void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().isEmpty()) {
            int row = GridPane.getRowIndex(clickedButton);
            int col = GridPane.getColumnIndex(clickedButton);
            if(numberOfPlayes%2==0) {
                updateButtonStyle(clickedButton, "X");
                gameStatus.setText(playerOName+"'s Turn");
                currentPlayer=playerXName;
                int flag = (playBoard.play(row, col,'x'));
                if(flag==1){

                    endGame(currentPlayer);
                }else if (flag ==0) endGame("draw");
                else
                    numberOfPlayes++;
            }
            else {
                updateButtonStyle(clickedButton, "O");
                gameStatus.setText(playerXName+"'s Turn");
                currentPlayer=playerOName;
                int flag = (playBoard.play(row, col,'o'));
                if(flag==1){
                    endGame(currentPlayer);
                }else if (flag==0)
                {
                    endGame("draw");

                }else{  numberOfPlayes++;}

            }

        }
    }

    private void updateButtonStyle(Button button, String symbol) {
        if ("X".equals(symbol)) {
            button.setStyle(
                    "-fx-background-color: rgba(70, 163, 255, 0.2); " +
                            "-fx-text-fill: #46A3FF; " +
                            "-fx-font-size: 36px; " +
                            "-fx-border-color: #E3E3E3;"
            );
            button.setText("X");
        } else if ("O".equals(symbol)) {
            button.setStyle(
                    "-fx-background-color: rgba(255, 130, 126, 0.2); " +
                            "-fx-text-fill: #FF827E; " +
                            "-fx-font-size: 36px; " +
                            "-fx-border-color: #E3E3E3;"
            );
            button.setText("O");
        }
    }
    private void endGame(String winner) {
        String message = "Player " + winner + " wins!";
        String videoPath ="";
        if (winner.equals("draw")) {  // Handle draw condition
            message = "It's a draw!";
            videoPath = "src/main/resources/com/example/tictactoegama/videos/video_draw2.mp4";
        } else {  // Handle win condition
            message = "Player " + winner + " wins!";
            videoPath = "src/main/resources/com/example/tictactoegama/videos/video_win.mp4";
        }

        gameStatus.setText(message);
        disableButtons();
        gameEnded = true;
        drawWinnerLine(playBoard.getWinningTiles());
        winnerLine.setVisible(true);
        final String finalVideoPath = videoPath;
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> {

            Stage stage = (Stage) gameGrid.getScene().getWindow();
            videoViewHandler.showVideoView(stage, finalVideoPath, gameGrid.getScene());
        } );


        delay.play();
    }

    private void disableButtons() {
        for (Node node : gameGrid.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
        }
        replayBtn.setVisible(true);

    }

    public void handleGotoHome(ActionEvent event) throws IOException {
        XScore=0;
        OScore=0;
        Parent optionPageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/options_page.fxml"));
        Scene optionPageScene = new Scene(optionPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(optionPageScene);
        window.show();
    }

    public void handleReplay(ActionEvent event) throws IOException {
        if(currentPlayer==playerXName){
            XScore+=1;
        } else if (currentPlayer==playerOName) {
            OScore+=1;
        }
        // Clear the game grid and reset the game state

        resetGame();
        Parent gamePageParent = FXMLLoader.load(getClass().getResource(
                "/com/example/tictactoegama/views/local-game-page.fxml"));
        Scene gamePageScene = new Scene(gamePageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gamePageScene);
        window.show();

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
    private void resetGame() {
        // Clear all buttons from the grid
        for (Node node : gameGrid.getChildren()) {
            if (node instanceof Button) { // Check if the node is a Button
                ((Button) node).setText(""); // Clear the text of the button
                node.setDisable(false); // Enable the button
                node.setStyle(""); // Reset button style if needed
            }
        }

        // Reset game state variables
        gameEnded = false;
        numberOfPlayes = 0;
        playBoard = new PlayBoard();
        currentPlayer = playerXName;

        // Reset UI elements
        playerXNametxt.setText(playerXName);
        playerONametxt.setText(playerOName);
        OScoreLabel.setText("" + OScore);
        XScoreLabel.setText("" + XScore);
        gameStatus.setText(playerXName + "'s Turn");
        winnerLine.setVisible(false);
        replayBtn.setVisible(false);
        gotoHomeBtn.setVisible(false);
    }
}