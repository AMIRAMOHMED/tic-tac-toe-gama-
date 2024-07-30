package com.example.tictactoegama.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.Socket;


import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.Api.RequestHandler;
import com.example.tictactoegama.models.User;

import javafx.application.Platform;
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
    private static ActionEvent event;
    @FXML
    private void handleRegister(ActionEvent event) throws IOException {
        // Handles register button click, opens the register screen
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/Register.fxml"));
        Scene registerScene = new Scene(root);
        stage.setScene(registerScene);
        stage.show();
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        this.event = event;
        // Handles login button click, validates input and communicates with the server
        String password = passwordtxt.getText();
        String username = userNametxt.getText();
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "All fields must be filled.");
            return;
        }
        User player = new User(username, password);
        String playerJson = player.toString(); // Convert Player object to JSON string
        ClientHandler.send( "{\"RequestType\":\"Login\", \"User\":"+ playerJson+"}");
    }
    public void getHomePage() throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/ListOfAvailablePlayers.fxml"));
        Scene registerScene = new Scene(root);
        stage.setScene(registerScene);
        stage.show();
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