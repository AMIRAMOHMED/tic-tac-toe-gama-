package com.example.tictactoegama;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OnlinePlayerListItem extends AnchorPane{

    protected final Text text;
    protected final SVGPath sVGPath;
    protected final Text text0;
    protected final Button button;

    public OnlinePlayerListItem(String playerName, boolean status){

        text = new Text();
        sVGPath = new SVGPath();
        text0 = new Text();
        button = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(50.0);
        setPrefWidth(700.0);

        text.setLayoutX(14.0);
        text.setLayoutY(20.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText(playerName);
        text.setFont(new Font("Segoe UI", 16.0));

        sVGPath.setContent("M7.8 10a2.2 2.2 0 0 0 4.4 0 2.2 2.2 0 0 0-4.4 0z");
        sVGPath.setFill(javafx.scene.paint.Color.valueOf("#00c096"));
        sVGPath.setLayoutX(6.0);
        sVGPath.setLayoutY(25.0);

        text0.setLayoutX(24.0);
        text0.setLayoutY(40.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("Online");

        button.setLayoutX(570.0);
        button.setLayoutY(13.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(25.0);
        button.setPrefWidth(102.0);
        button.setStyle("-fx-border-color: #000000;");
        button.setBackground(Background.EMPTY);
        button.setText("invite");

        getChildren().add(text);
        getChildren().add(sVGPath);
        getChildren().add(text0);
        getChildren().add(button);

    }



}
