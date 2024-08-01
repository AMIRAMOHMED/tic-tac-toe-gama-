package com.example.tictactoegama.controller;

import com.example.tictactoegama.Api.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;



public class SetIPController {

    @FXML
    private TextField ipAddressField;

    @FXML
    private Button continueButton;

    @FXML
    private Text statusText;


    @FXML
    private void initialize() {
        continueButton.setOnAction(event -> {
            try {
                handleContinue(event);
            } catch (IOException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void handleContinue(javafx.event.ActionEvent event) throws IOException, InstantiationException {
        String ipAddress = ipAddressField.getText();
      Client.init(ipAddress,5005);
      Client client = Client.getInstance();


        if (client.isConnected()) {
            statusText.setText("Connection successful!");
            statusText.setFill(javafx.scene.paint.Color.GREEN);
            Parent gamePageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/Login.fxml"));
            Scene gamePageScene = new Scene(gamePageParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(gamePageScene);
            window.show();
        } else
        {

            statusText.setText("Connection failed, please try again.");
            statusText.setFill(javafx.scene.paint.Color.RED);
        }
    }
}