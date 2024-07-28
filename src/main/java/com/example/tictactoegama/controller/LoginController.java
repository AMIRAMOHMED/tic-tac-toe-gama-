package com.example.tictactoegama.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.models.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONObject;

/**
 *
 * @author Mohamed Fekry Khedr
 */
public class LoginController {

    @FXML
    private PasswordField passwordtxt;
    @FXML
    private TextField userNametxt;

    @FXML
    private Button registerBtn;
    @FXML
    private Button loginBtn;

    static Client client;

    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
        Parent gamePageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/Register.fxml"));
        Scene gamePageScene = new Scene(gamePageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gamePageScene);
        window.show();

    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String password = passwordtxt.getText();
        String username = userNametxt.getText();
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }

        User player = new User(username, password);
        String playerJson = player.toString(); // Convert Player object to JSON string

        System.out.println("Player JSON: " + playerJson); // Log the JSON string

        if (client != null && client.isConnected()) {
            Socket socket = client.getSocket();
            try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                 DataInputStream dis = new DataInputStream(socket.getInputStream())) {

                dos.writeUTF("{\"RequestType\":\"Login\", \"User\":" + playerJson + "}");
                System.out.println("Data sent to server successfully."); // Log success message

                String response = dis.readUTF(); // Use readUTF instead of readLine
                System.out.println(response);
                if ("1".equals(response)) {
                    Parent gamePageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/ListOfAvailablePlayers.fxml"));
                    Scene gamePageScene = new Scene(gamePageParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(gamePageScene);
                    window.show();
                } else {
                    showAlert("Error", "Login failed. Please check your credentials.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to communicate with server.");
            }
        } else {
            showAlert("Error", "Not connected to server.");
        }
    }


    private void showAlert(String title, String message) {
        // Shows an alert with the provided title and message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}