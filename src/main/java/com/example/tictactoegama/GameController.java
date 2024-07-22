package com.example.tictactoegama;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    @FXML
    public void initialize() {
        Platform.runLater(this::showSymbolSelectionDialog);
        playBoard = new PlayBoard();
        gameEnded = false;
    }

    private void showSymbolSelectionDialog() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.UNDECORATED); // Remove the app bar
        dialogStage.setWidth(300); // Set fixed width
        dialogStage.setHeight(200); // Set fixed height
        dialogStage.centerOnScreen(); // Center on the screen

        dialogStage.setOnCloseRequest(Event::consume);

        Text headerText = new Text("Choose Your Symbol");
        headerText.setFont(Font.font(20));
        headerText.setFill(Color.BLACK); // Set text color to black

        Button buttonX = new Button("X");
        buttonX.setFont(Font.font(18));
        buttonX.setMinSize(100, 50);
        buttonX.setMaxSize(100, 50);
        buttonX.setStyle(
                "-fx-background-color: #46A3FF; " +
                        "-fx-text-fill: #FFFFFF; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5px;" +
                        "-fx-border-color: #B0B0B0; " +
                        "-fx-border-width: 2px;"
        );

        Button buttonO = new Button("O");
        buttonO.setFont(Font.font(18));
        buttonO.setMinSize(100, 50);
        buttonO.setMaxSize(100, 50);
        buttonO.setStyle(
                "-fx-background-color: #FF827E; " +
                        "-fx-text-fill: #FFFFFF; " + // Text color white
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5px;" +
                        "-fx-border-color: #B0B0B0; " + // Gray border
                        "-fx-border-width: 2px;" // Border width
        );

        // Set action events
        buttonX.setOnAction(e -> {
            currentPlayer = "X";
            computerSymbol = "O";
            gameStatus.setText("Current Player: " + currentPlayer);
            dialogStage.close();
        });

        buttonO.setOnAction(e -> {
            currentPlayer = "O";
            computerSymbol = "X";
            gameStatus.setText("Current Player: " + currentPlayer);
            dialogStage.close();
        });

        HBox buttonBox = new HBox(10, buttonX, buttonO);
        buttonBox.setAlignment(Pos.CENTER);

        VBox dialogVBox = new VBox(20, headerText, buttonBox);
        dialogVBox.setAlignment(Pos.CENTER);
        dialogVBox.setPadding(new Insets(20));
        dialogVBox.setStyle(
                "-fx-background-color: #FFFFFF; " + // White background
                        "-fx-border-color: #B0B0B0; " + // Gray border
                        "-fx-border-width: 2px; " + // Border width
                        "-fx-border-radius: 10px; " + // Rounded corners
                        "-fx-background-radius: 10px;" // Rounded corners
        );

        Scene dialogScene = new Scene(dialogVBox);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        if (gameEnded) {
            return; // Ignore clicks if the game has ended
        }

        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().isEmpty()) {
            int row = GridPane.getRowIndex(clickedButton);
            int col = GridPane.getColumnIndex(clickedButton);

            // Player move
            clickedButton.setText(currentPlayer);
            updateButtonStyle(clickedButton, currentPlayer);
            playBoard.play(row, col, currentPlayer.charAt(0));

            // Check for player win
            int result = playBoard.checkWin(row, col, currentPlayer.charAt(0));
            if (result != -1) {
                endGame(result);
                return;
            }

            // Computer move
            playBoard.mediumMood(computerSymbol.charAt(0));
            updateBoardUI();

            // Check for computer win
            char[][] board = playBoard.getBoard();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == computerSymbol.charAt(0)) {
                        result = playBoard.checkWin(i, j, computerSymbol.charAt(0));
                        if (result != -1) {
                            endGame(result);
                            return;
                        }
                    }
                }
            }
        }
    }

    private void updateButtonStyle(Button button, String symbol) {
        if ("X".equals(symbol)) {
            button.setStyle(
                    "-fx-background-color: rgba(70, 163, 255, 0.2); " + // Semi-transparent background
                            "-fx-text-fill: #46A3FF; " + // Text color matching the background
                            "-fx-font-size: 36px; " +
                            "-fx-border-color: #E3E3E3; " // Border color
            );
        } else if ("O".equals(symbol)) {
            button.setStyle(
                    "-fx-background-color: rgba(255, 130, 126, 0.2); " + // Semi-transparent background
                            "-fx-text-fill: #FF827E; " + // Text color matching the background
                            "-fx-font-size: 36px; " +
                            "-fx-border-color: #E3E3E3; " // Border color
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
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return (Button) node;
            }
        }
        return null;
    }

    private void endGame(int result) {
        String message;
        if (result == 1) {
            message = "Player " + currentPlayer + " wins!";
            disableButtons();

        } else {
            message = "It's a draw!";
        }
        gameStatus.setText(message);

        gameEnded = true; // Mark the game as ended
    }




    private double getButtonCenterX(int row, int col) {
        Button button = getNodeByRowColumnIndex(row, col);
        if (button != null) {
            return button.localToScene(button.getWidth() / 2, 0).getX();
        }
        return 0;
    }


    private double getButtonCenterY(int row, int col) {
        Button button = getNodeByRowColumnIndex(row, col);
        if (button != null) {
            return button.localToScene(0, button.getHeight() / 2).getY();
        }
        return 0;
    }

    private void disableButtons() {
        for (Node node : gameGrid.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
        }
    }
}
