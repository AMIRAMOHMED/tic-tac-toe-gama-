package com.example.tictactoegama.controller;

import com.example.tictactoegama.logic.FileHandler;
import com.example.tictactoegama.models.GameMoves;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class savehistoryrequestBase extends AnchorPane {

    protected final Text text;
    protected final Button yesbutton;
    protected final Button nobutton;
    public savehistoryrequestBase(Stage stage , GameMoves gameMoves) {

        text = new Text();
        yesbutton = new Button();
        nobutton = new Button();

        setId("AnchorPane");
        setPrefHeight(200.0);
        setPrefWidth(400.0);

        text.setLayoutX(93.0);
        text.setLayoutY(59.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Do you Want To save the Game?");
        text.setFont(new Font("Segoe UI Bold", 14.0));

        yesbutton.setLayoutX(122.0);
        yesbutton.setLayoutY(100.0);
        yesbutton.setMnemonicParsing(false);
        yesbutton.setStyle("-fx-background-color: #46A3FF;");
        yesbutton.setText("Yes");
        yesbutton.setTextFill(javafx.scene.paint.Color.WHITE);
        yesbutton.setFont(new Font("Segoe UI", 14.0));
        yesbutton.setOnAction(event -> {
            FileHandler file = new FileHandler();
            try {
                file.writeFile(gameMoves.toString());
                stage.close();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Error while saving the file");
            }
        });

        nobutton.setLayoutX(239.0);
        nobutton.setLayoutY(100.0);
        nobutton.setMnemonicParsing(false);
        nobutton.setStyle("-fx-background-color: #FF827E;");
        nobutton.setText("No");
        nobutton.setTextFill(javafx.scene.paint.Color.WHITE);
        nobutton.setFont(new Font("Segoe UI", 14.0));
        nobutton.setOnAction(event -> {
            stage.close();
        });

        getChildren().add(text);
        getChildren().add(yesbutton);
        getChildren().add(nobutton);

    }
}
