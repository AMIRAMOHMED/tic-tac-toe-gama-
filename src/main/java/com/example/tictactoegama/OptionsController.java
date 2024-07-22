package com.example.tictactoegama;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class OptionsController {
    @FXML
    private void handleButtonActionPlayWithComputur(ActionEvent event) throws IOException {
        Parent gamePageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/gama-page.fxml"));
        Scene gamePageScene = new Scene(gamePageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gamePageScene);
        window.show();
    }


    public void handlePlayWithFriends(ActionEvent event) throws IOException {
        Parent gamePageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/getNamesOfTwoPlayers.fxml"));
        Scene gamePageScene = new Scene(gamePageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gamePageScene);
        window.show();
    }
}