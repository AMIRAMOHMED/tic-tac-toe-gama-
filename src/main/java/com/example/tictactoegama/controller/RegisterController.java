/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tictactoegama.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
