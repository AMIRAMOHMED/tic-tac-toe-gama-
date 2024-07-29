package com.example.tictactoegama.controller;
import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class requestAlertBoxBase extends TitledPane {

    protected final AnchorPane anchorPane;
    protected final Text playername;
    protected final FlowPane flowPane;
    protected final VBox vBox;
    protected final Text wins;
    protected final Text text;
    protected final Line line;
    protected final VBox vBox0;
    protected final Text losses;
    protected final Text text0;
    protected final Line line0;
    protected final VBox vBox1;
    protected final Text draws;
    protected final Text text1;
    protected final Text accept;
    protected final Text decline;
    protected final Text text2;
    public requestAlertBoxBase(JSONObject obj , Stage stage) {
        JSONObject object = obj.getJSONObject("Player");
        anchorPane = new AnchorPane();
        playername = new Text();
        flowPane = new FlowPane();
        vBox = new VBox();
        wins = new Text();
        text = new Text();
        line = new Line();
        vBox0 = new VBox();
        losses = new Text();
        text0 = new Text();
        line0 = new Line();
        vBox1 = new VBox();
        draws = new Text();
        text1 = new Text();
        accept = new Text();
        decline = new Text();
        text2 = new Text();

        setAnimated(false);
        setCollapsible(false);
        setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(200.0);
        setPrefWidth(400.0);
        setText("Player Request");

        playername.setLayoutX(131.0);
        playername.setLayoutY(37.0);
        playername.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        playername.setStrokeWidth(0.0);
        playername.setText(object.getString("username"));
        playername.setFont(new Font("Segoe UI Bold", 24.0));

        flowPane.setHgap(10.0);
        flowPane.setLayoutX(99.0);
        flowPane.setLayoutY(52.0);
        flowPane.setPrefHeight(53.0);
        flowPane.setPrefWidth(200.0);

        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setPrefHeight(53.0);
        vBox.setPrefWidth(50.0);

        wins.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        wins.setStrokeWidth(0.0);
        wins.setText(""+object.getInt("wins"));
        wins.setFont(new Font("Segoe UI", 24.0));

        text.setFill(javafx.scene.paint.Color.valueOf("#adadad"));
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Wins");
        text.setFont(new Font("Segoe UI", 14.0));

        line.setEndX(21.0);
        line.setEndY(-12.0);
        line.setStartX(21.0);
        line.setStartY(40.0);
        line.setStroke(javafx.scene.paint.Color.valueOf("#e3e3e3"));

        vBox0.setAlignment(javafx.geometry.Pos.CENTER);
        vBox0.setPrefHeight(50.0);
        vBox0.setPrefWidth(50.0);

        losses.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        losses.setStrokeWidth(0.0);
        losses.setText(""+object.getInt("losses"));
        losses.setFont(new Font("Segoe UI", 24.0));

        text0.setFill(javafx.scene.paint.Color.valueOf("#adadad"));
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("Losses");
        text0.setFont(new Font("Segoe UI", 14.0));

        line0.setEndX(21.0);
        line0.setEndY(-12.0);
        line0.setStartX(21.0);
        line0.setStartY(40.0);
        line0.setStroke(javafx.scene.paint.Color.valueOf("#e3e3e3"));

        vBox1.setAlignment(javafx.geometry.Pos.CENTER);
        vBox1.setPrefHeight(50.0);
        vBox1.setPrefWidth(50.0);

        draws.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        draws.setStrokeWidth(0.0);
        draws.setText(""+object.getInt("draws"));
        draws.setFont(new Font("Segoe UI", 24.0));

        text1.setFill(javafx.scene.paint.Color.valueOf("#adadad"));
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("Draws");
        text1.setFont(new Font("Segoe UI", 14.0));

        accept.setFill(javafx.scene.paint.Color.valueOf("#46a3ff"));
        accept.setLayoutX(109.0);
        accept.setLayoutY(153.0);
        accept.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        accept.setStrokeWidth(0.0);
        accept.setText("Accept");
        accept.setFont(new Font("Segoe UI Semibold", 14.0));
        accept.setOnMouseClicked(event -> {
                ClientHandler.send("{\"RequestType\":\"RequestGame\",\"accepted\":"+true+"}");
                stage.close();
        });

        decline.setFill(javafx.scene.paint.Color.valueOf("#ff827e"));
        decline.setLayoutX(237.0);
        decline.setLayoutY(152.0);
        decline.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        decline.setStrokeWidth(0.0);
        decline.setText("Decline");
        decline.setFont(new Font("Segoe UI Semibold", 14.0));
        decline.setOnMouseClicked(event -> {
           ClientHandler.send("{\"RequestType\":\"RequestGame\",\"accepted\":"+false+"}");
           stage.close();
        });

        text2.setLayoutX(138.0);
        text2.setLayoutY(126.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("Wants to play with you");
        text2.setFont(new Font("Segoe UI", 12.0));
        setContent(anchorPane);

        anchorPane.getChildren().add(playername);
        vBox.getChildren().add(wins);
        vBox.getChildren().add(text);
        flowPane.getChildren().add(vBox);
        flowPane.getChildren().add(line);
        vBox0.getChildren().add(losses);
        vBox0.getChildren().add(text0);
        flowPane.getChildren().add(vBox0);
        flowPane.getChildren().add(line0);
        vBox1.getChildren().add(draws);
        vBox1.getChildren().add(text1);
        flowPane.getChildren().add(vBox1);
        anchorPane.getChildren().add(flowPane);
        anchorPane.getChildren().add(accept);
        anchorPane.getChildren().add(decline);
        anchorPane.getChildren().add(text2);

    }
}
