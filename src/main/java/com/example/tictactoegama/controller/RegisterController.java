package com.example.tictactoegama.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.Api.RequestHandler;
import com.example.tictactoegama.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.json.JSONObject;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.models.Player;

/**
 * Controller class for handling user registration and login.
 */
public class RegisterController {

    @FXML
    private PasswordField passwordtxt;
    @FXML
    private PasswordField confirmPasswordtxt;
    @FXML
    private TextField emailtxt;
    @FXML
    private TextField userNametxt;
    @FXML
    private DatePicker birthDatetxt;
    @FXML
    private Button registerBtn;
    @FXML
    private Button loginBtn;

    static  Client client;

//   static public void setClient() {
//
//    }


    @FXML
    private void handleRegister(ActionEvent event) {
        String email = emailtxt.getText();
        String password = passwordtxt.getText();
        String confirmPassword = confirmPasswordtxt.getText();
        String username = userNametxt.getText();
        int userId = 0;
        String birthDate = birthDatetxt.getValue() != null ? birthDatetxt.getValue().toString() : "";

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || username.isEmpty() || birthDate.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match.");
            return;
        }

        User player = new User(userId, username, email, password);
        String playerJson = player.toString(); // Convert Player object to JSON string

        System.out.println("Player JSON: " + playerJson); // Log the JSON string
        ClientHandler.send("{\"RequestType\":\"Register\" ,\"User\":"+ playerJson+"}");
            String resulr= null;
            resulr = RequestHandler.getResponse();
            System.out.println(resulr); // Log the request JSON
            System.out.println("Data sent to server successfully."); // Log success message

    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}