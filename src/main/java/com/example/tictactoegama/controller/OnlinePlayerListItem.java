package com.example.tictactoegama.controller;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.Api.RequestHandler;
import com.example.tictactoegama.logic.OnlineGamePlay;
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
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class OnlinePlayerListItem extends AnchorPane{

    protected final Text text;
    protected final SVGPath sVGPath;
    protected final Text text0;
    protected final Button inviteButton;
    private Client client;
    public OnlinePlayerListItem(String playerName, boolean status , int userid){
        try {
            client = Client.getInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
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
        text.setText(playerName);
        text.setFont(new Font("Segoe UI", 16.0));

        sVGPath.setContent("M7.8 10a2.2 2.2 0 0 0 4.4 0 2.2 2.2 0 0 0-4.4 0z");
        sVGPath.setFill(javafx.scene.paint.Color.valueOf(status ? "#FF827E" :"#00c096" ));
        sVGPath.setLayoutX(6.0);
        sVGPath.setLayoutY(25.0);

        text0.setLayoutX(24.0);
        text0.setLayoutY(40.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText(status ? "ingame" : "online");

        inviteButton.setLayoutX(570.0);
        inviteButton.setLayoutY(13.0);
        inviteButton.setMnemonicParsing(false);
        inviteButton.setPrefHeight(25.0);
        inviteButton.setPrefWidth(102.0);
        inviteButton.setStyle("-fx-border-color: #000000;");
        inviteButton.setBackground(Background.EMPTY);
        inviteButton.setText("invite");
        inviteButton.setOnAction(
                event ->new Thread( new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ClientHandler.send( "{\"RequestType\":\"RequestGame\",\"userid\":"+Client.userid+",\"opponentid\":"+userid+"}");
                            String msg;
                            while ((msg = RequestHandler.getResponse()) != null) {
                                JSONObject object = new JSONObject(msg);
                                boolean accepted = object.getBoolean("accepted");
                                if (accepted) {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tictactoegama/views/gama-page.fxml"));
                                    Parent gamePageParent = loader.load();

                                    GameController gameController = loader.getController();
                                    gameController.setAiMoodOption(new OnlineGamePlay());

                                    Scene gamePageScene = new Scene(gamePageParent);
                                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    window.setScene(gamePageScene);
                                    window.show();
                                } else {
                                    inviteButton.setText("Denied");
                                    inviteButton.setStyle("-fx-background-color: #FF827E");
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start()
        );
        getChildren().add(text);
        getChildren().add(sVGPath);
        getChildren().add(text0);
        getChildren().add(inviteButton);

    }
}