package com.example.tictactoegama.controller;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OnlinePlayerListItem extends AnchorPane {

    protected final Text playerNameText;
    protected final SVGPath statusIcon;
    protected final Text statusText;
    protected final Button inviteButton;
    private final int playerId;

    public OnlinePlayerListItem(String playerName,int id, boolean status) {
        this.playerId = id;
        playerNameText = new Text();
        statusIcon = new SVGPath();
        statusText = new Text();
        inviteButton = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(50.0);
        setPrefWidth(700.0);

        playerNameText.setLayoutX(14.0);
        playerNameText.setLayoutY(20.0);
        playerNameText.setStrokeType(StrokeType.OUTSIDE);
        playerNameText.setStrokeWidth(0.0);
        playerNameText.setText(playerName);
        playerNameText.setFont(new Font("Segoe UI", 16.0));

        statusIcon.setContent("M7.8 10a2.2 2.2 0 0 0 4.4 0 2.2 2.2 0 0 0-4.4 0z");
        statusIcon.setFill(status ? Color.valueOf("#ff0000"): Color.valueOf("#00c096"));
        statusIcon.setLayoutX(6.0);
        statusIcon.setLayoutY(25.0);

        statusText.setLayoutX(24.0);
        statusText.setLayoutY(40.0);
        statusText.setStrokeType(StrokeType.OUTSIDE);
        statusText.setStrokeWidth(0.0);
        statusText.setText(status ? "ingame" : "online");

        inviteButton.setLayoutX(570.0);
        inviteButton.setLayoutY(13.0);
        inviteButton.setMnemonicParsing(false);
        inviteButton.setPrefHeight(25.0);
        inviteButton.setPrefWidth(102.0);
        inviteButton.setStyle("-fx-border-color: #000000;");
        inviteButton.setBackground(Background.EMPTY);
        inviteButton.setText("Invite");

        getChildren().addAll(playerNameText, statusIcon, statusText, inviteButton);

        inviteButton.setOnAction(event -> {
            ListOfAvailablePlayersController.requestGame(playerId);
        });
    }
}
