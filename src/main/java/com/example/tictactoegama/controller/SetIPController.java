package com.example.tictactoegama.controller;

import com.example.tictactoegama.Api.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SetIPController {

    @FXML
    private TextField ipAddressField;

    @FXML
    private Button continueButton;

    @FXML
    private Text statusText;

    @FXML
    private void initialize() {
        continueButton.setOnAction(event -> handleContinue());
    }

    private void handleContinue() {
        String ipAddress = ipAddressField.getText();
        Client client = new Client(ipAddress, 5005); // Try to create a client with the provided IP address
        if (client.isConnected()) {
            statusText.setText("Connection successful!");
            statusText.setFill(javafx.scene.paint.Color.GREEN);
            // Proceed to the next scene or functionality
        } else {
            statusText.setText("Connection failed, please try again.");
            statusText.setFill(javafx.scene.paint.Color.RED);
        }
    }


}
