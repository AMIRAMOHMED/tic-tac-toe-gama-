package com.example.tictactoegama.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.Api.RequestHandler;
import com.example.tictactoegama.models.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Mohamed Fekry Khedr
 */
public class Scoreboard implements Initializable {
    
    @FXML
    ListView<viewListIScoreboardBase> nameList;

    Client client;
    DataInputStream input;
    DataOutputStream output;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ClientHandler.send( "{\"RequestType\":\"Scoreboard\"}");
        Platform.runLater(()->{
            Vector<Player> players = RequestHandler.getScoreBoard();
            for (int i = 0; i < players.size(); i++) {
                nameList.getItems().add(new viewListIScoreboardBase(i+". "+ players.get(i).getUsername(), players.get(i).getScore()));
            }
        });
    }    
    
    
}
