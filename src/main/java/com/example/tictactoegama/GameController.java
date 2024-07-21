package com.example.tictactoegama;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    @FXML
    public void initialize() {
        Platform.runLater(this::showSymbolSelectionDialog);
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
                "-fx-background-color: #46A3FF; " + // Use the specified color
                        "-fx-text-fill: #FFFFFF; " + // Text color white
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5px;" +
                        "-fx-border-color: #B0B0B0; " + // Gray border
                        "-fx-border-width: 2px;" // Border width
        );

        Button buttonO = new Button("O");
        buttonO.setFont(Font.font(18));
        buttonO.setMinSize(100, 50);
        buttonO.setMaxSize(100, 50);
        buttonO.setStyle(
                "-fx-background-color: #FF827E; " + // Use the specified color
                        "-fx-text-fill: #FFFFFF; " + // Text color white
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5px;" +
                        "-fx-border-color: #B0B0B0; " + // Gray border
                        "-fx-border-width: 2px;" // Border width
        );

        // Set action events
        buttonX.setOnAction(e -> {
            currentPlayer = "X";
            gameStatus.setText("Current Player: " + currentPlayer);
            dialogStage.close();
        });

        buttonO.setOnAction(e -> {
            currentPlayer = "O";
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
    private void handleButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().isEmpty()) {
            if ("X".equals(currentPlayer)) {
                clickedButton.setText(currentPlayer);
                clickedButton.setStyle(
                        "-fx-background-color: rgba(70, 163, 255, 0.2); " + // Semi-transparent background
                                "-fx-text-fill: #46A3FF; " + // Text color matching the background
                                "-fx-font-size: 36px; " +
                                "-fx-border-color: #E3E3E3; " // Border color
                );
            } else if ("O".equals(currentPlayer)) {
                clickedButton.setText(currentPlayer);
                clickedButton.setStyle(
                        "-fx-background-color: rgba(255, 130, 126, 0.2); " + // Semi-transparent background
                                "-fx-text-fill: #FF827E; " + // Text color matching the background
                                "-fx-font-size: 36px; " +
                                "-fx-border-color: #E3E3E3; " // Border color
                );
            }
        }
    }
}
