package com.example.tictactoegama.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.example.tictactoegama.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
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


    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        Parent gamePageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/Login.fxml"));
        Scene gamePageScene = new Scene(gamePageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gamePageScene);
        window.show();
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        String email = emailtxt.getText();
        String password = passwordtxt.getText();
        String confirmPassword = confirmPasswordtxt.getText();
        String username = userNametxt.getText();
        int userId = 0;


        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || username.isEmpty() ) {
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

        try {
            if (client != null && client.isConnected()) {
                Socket socket = client.getSocket();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                JSONObject request = new JSONObject();
                request.put("RequestType", "Register");
                request.put("User", playerJson);

                System.out.println("Request JSON: " + request.toString()); // Log the request JSON

                dos.writeUTF("{\"RequestType\":\"Register\" ,\"User\":"+ playerJson+"}");
//                dos.flush();

                System.out.println("Data sent to server successfully."); // Log success message
            } else {
                showAlert("Error", "Not connected to server.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to communicate with server.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}