/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tictactoegama.controller;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.tictactoegama.Api.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;



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
    @FXML
    TextField txt;
    @FXML
    FlowPane noplayers;

    Text playername;
    SVGPath status;
    Button invite;
    ArrayList<OnlinePlayerListItem> items;
    Client client;
    DataOutputStream output;
    DataInputStream input;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


}
