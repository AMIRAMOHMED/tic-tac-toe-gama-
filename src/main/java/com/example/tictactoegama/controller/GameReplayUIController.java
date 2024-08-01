package com.example.tictactoegama.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.json.JSONArray;

import java.io.IOException;

public class GameReplayUIController {
    @FXML
    Button btn00,btn01,btn02,btn10,btn11,btn12,btn20,btn21,btn22;
    @FXML
    private GridPane gameGrid;
    private static JSONArray moves;
    private boolean isX=true;
    private int counter=0;

    public GameReplayUIController() {
    }

    public GameReplayUIController(JSONArray gameMoves) {
        moves=gameMoves;
    }
    @FXML
    public void handleButtonClick(ActionEvent event) {

    }
    @FXML
    public void handleMoveOn(ActionEvent event) {
        int move=0;
        if(counter< moves.length()){
            move = (int) moves.get(counter);
            counter+=1;
            switch (move){
                case 0:
                    updateButtonStyle(btn00);
                    break;
                case 1:
                    updateButtonStyle(btn01);
                    break;
                case 2:
                    updateButtonStyle(btn02);
                    break;
                case 3:
                    updateButtonStyle(btn10);
                    break;
                case 4:
                    updateButtonStyle(btn11);
                    break;
                case 5:
                    updateButtonStyle(btn12);
                    break;
                case 6:
                    updateButtonStyle(btn20);
                    break;
                case 7:
                    updateButtonStyle(btn21);
                    break;
                case 8:
                    updateButtonStyle(btn22);
                    break;
            }
        }else {
            counter=0;
            isX=true;
            eraseGame();
        }
    }

    private void eraseGame() {
        for (Node node : gameGrid.getChildren()) {
            if (node instanceof Button) {
                node.setStyle(
                        "-fx-background-color: #FFFFFF; " +
                                "-fx-border-color: #FFFFFF;"+
                                "-fx-border-color: #E3E3E3;"
                );
                ((Button) node).setText("");
            }
        }
    }

    @FXML
    public void handleMoveBack(ActionEvent event) {
        int move;
        if(counter> 0){
            move = (int) moves.get(counter-1);
            counter-=1;
            switch (move){
                case 0:
                    eraseStyle(btn00);
                    break;
                case 1:
                    eraseStyle(btn01);
                    break;
                case 2:
                    eraseStyle(btn02);
                    break;
                case 3:
                    eraseStyle(btn10);
                    break;
                case 4:
                    eraseStyle(btn11);
                    break;
                case 5:
                    eraseStyle(btn12);
                    break;
                case 6:
                    eraseStyle(btn20);
                    break;
                case 7:
                    eraseStyle(btn21);
                    break;
                case 8:
                    eraseStyle(btn22);
                    break;
            }
        }else {
            counter=0;
            isX=true;
        }


    }
    @FXML
    public void handleGoBack(ActionEvent event) throws IOException {
        Parent gamePageParent = null;
        gamePageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/HistoryPage.fxml"));
        Scene gamePageScene = new Scene(gamePageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gamePageScene);
        window.show();
    }
    private void updateButtonStyle(Button button) {
        String symbol;
        if (isX){
            symbol="X";
            isX=false;
        }else{
            symbol="O";
            isX=true;
        }
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
    private void eraseStyle(Button button) {
        String symbol;
        if (isX){
            symbol="X";
            isX=false;
        }else{
            symbol="O";
            isX=true;
        }

        button.setStyle(
                "-fx-background-color: #FFFFFF; " +
                        "-fx-border-color: #FFFFFF;"+
                        "-fx-border-color: #E3E3E3;"
        );
        button.setText("");
    }
}
