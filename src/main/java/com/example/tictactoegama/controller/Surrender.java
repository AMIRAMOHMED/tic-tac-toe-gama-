package com.example.tictactoegama.controller;

import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.TicTacToeGama;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Surrender implements Initializable {
    @FXML
    Button leaveButton;
    @FXML
    Button continueButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leaveButton.setOnAction(event -> {
            ClientHandler.send("{\"RequestType\":\"Surrender\",\"opponent\":"+OnlineGameController.opponent+"}");
            Parent optionPageParent = null;
            try {
                optionPageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/ListOfAvailablePlayers.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene optionPageScene = new Scene(optionPageParent);
            Stage window = TicTacToeGama.getStage();
            window.setScene(optionPageScene);
            Stage dialog = (Stage) ((Node) event.getSource()).getScene().getWindow();
            dialog.close();
            window.show();
        });
        continueButton.setOnAction(event -> {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.close();
        });
    }
}
