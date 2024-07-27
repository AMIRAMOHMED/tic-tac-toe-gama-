package com.example.tictactoegama.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class getNamesController {
    @FXML
    TextField playerXName,playerOName;

    public void handleGotoGame(ActionEvent event) throws IOException {

        LocalGameController.playerXName=playerXName.getText();
        LocalGameController.playerOName=playerOName.getText();
        Parent gamePageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/local-game-page.fxml"));
        Scene gamePageScene = new Scene(gamePageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gamePageScene);
        window.show();
    }
}
