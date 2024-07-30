package com.example.tictactoegama.controller;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.logic.OnlineGamePlay;
import com.example.tictactoegama.models.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class OnlinePlayerListItem extends AnchorPane{

    protected final Text text;
    protected final SVGPath sVGPath;
    protected final Text text0;
    protected final Button inviteButton;
    private static ActionEvent event;
    public OnlinePlayerListItem(Player player){
        text = new Text();
        sVGPath = new SVGPath();
        text0 = new Text();
        inviteButton = new Button();

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
        text.setText(player.getUsername());
        text.setFont(new Font("Segoe UI", 16.0));

        sVGPath.setContent("M7.8 10a2.2 2.2 0 0 0 4.4 0 2.2 2.2 0 0 0-4.4 0z");
        sVGPath.setFill(javafx.scene.paint.Color.valueOf(player.isIsingame() ? "#FF827E" :"#00c096" ));
        sVGPath.setLayoutX(6.0);
        sVGPath.setLayoutY(25.0);

        text0.setLayoutX(24.0);
        text0.setLayoutY(40.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText(player.isIsingame() ? "ingame" : "online");

        inviteButton.setLayoutX(570.0);
        inviteButton.setLayoutY(13.0);
        inviteButton.setMnemonicParsing(false);
        inviteButton.setPrefHeight(25.0);
        inviteButton.setPrefWidth(102.0);
        inviteButton.setStyle("-fx-border-color: #000000;");
        inviteButton.setBackground(Background.EMPTY);
        inviteButton.setText("invite");
        inviteButton.setOnAction((event)->{
            this.event=event;
            ClientHandler.send( "{\"RequestType\":\"RequestGame\",\"userid\":"+Client.userid+",\"opponentid\":"+player.getUserid()+"}");
            Platform.runLater(()-> inviteButton.setText(". . ."));
            }
        );
        getChildren().add(text);
        getChildren().add(sVGPath);
        getChildren().add(text0);
        getChildren().add(inviteButton);

    }
}