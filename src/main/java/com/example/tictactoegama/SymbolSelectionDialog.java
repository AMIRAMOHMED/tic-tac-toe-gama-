package com.example.tictactoegama;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SymbolSelectionDialog {

    private final GameController controller; // Direct reference to GameController

    public SymbolSelectionDialog(GameController controller) {
        this.controller = controller;
    }

    public void show() {
        Stage dialogStage = createDialogStage();

        Text headerText = createHeaderText("Choose Your Symbol");
        Button buttonX = createSymbolButton("X", "#46A3FF");
        Button buttonO = createSymbolButton("O", "#FF827E");

        buttonX.setOnAction(e -> handleSymbolSelection("X", "O", dialogStage));
        buttonO.setOnAction(e -> handleSymbolSelection("O", "X", dialogStage));

        HBox buttonBox = createButtonBox(buttonX, buttonO);
        VBox dialogVBox = createDialogVBox(headerText, buttonBox);

        Scene dialogScene = new Scene(dialogVBox);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }

    private void handleSymbolSelection(String playerSymbol, String compSymbol, Stage dialogStage) {
        controller.handleSymbolSelection(playerSymbol, compSymbol); // Direct call to GameController method
        dialogStage.close();
    }

    private Stage createDialogStage() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setWidth(300);
        dialogStage.setHeight(200);
        dialogStage.centerOnScreen();
        return dialogStage;
    }

    private Text createHeaderText(String text) {
        Text headerText = new Text(text);
        headerText.setFont(Font.font(20));
        headerText.setFill(Color.BLACK);
        return headerText;
    }

    private Button createSymbolButton(String symbol, String color) {
        Button button = new Button(symbol);
        button.setFont(Font.font(18));
        button.setMinSize(100, 50);
        button.setMaxSize(100, 50);
        button.setStyle(
                "-fx-background-color: " + color + "; " +
                        "-fx-text-fill: #FFFFFF; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 5px; " +
                        "-fx-border-color: #B0B0B0; " +
                        "-fx-border-width: 2px;"
        );
        return button;
    }

    private HBox createButtonBox(Button buttonX, Button buttonO) {
        HBox buttonBox = new HBox(10, buttonX, buttonO);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }

    private VBox createDialogVBox(Text headerText, HBox buttonBox) {
        VBox dialogVBox = new VBox(20, headerText, buttonBox);
        dialogVBox.setAlignment(Pos.CENTER);
        dialogVBox.setPadding(new Insets(20));
        dialogVBox.setStyle(
                "-fx-background-color: #FFFFFF; " +
                        "-fx-border-color: #B0B0B0; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 10px; " +
                        "-fx-background-radius: 10px;"
        );
        return dialogVBox;
    }
}
