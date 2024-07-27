package com.example.tictactoegama.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.security.util.Password;

/**
 *
 * @author Mohamed Fekry Khedr
 */
public class RegisterController implements Initializable {

    @FXML
    PasswordField passwordtxt;
    @FXML
    TextField emailtxt;
    @FXML
    Button loginBtn;

    @FXML
    private void handleRegister(ActionEvent event) throws IOException {

        /*Stage stage =new Stage();

        Parent root;

        root = FXMLLoader.load(getClass().getResource("Video.fxml"));
        Scene registerScene = new Scene(root);
        stage.setScene(registerScene);
        stage.show();*/

    }
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
