/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tictactoegama;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author filop
 */
public class ListOfAvailablePlayersController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    FlowPane onlinePlayers;
    
    Text playername;
    SVGPath status;
    Button invite;
    OnlinePlayerListItem[] items;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items = new OnlinePlayerListItem[5];
        for (int i = 0 ; i<10 ; i++)
        onlinePlayers.getChildren().add(new OnlinePlayerListItem("PlayerName "+i, true));
    }    
    
}
