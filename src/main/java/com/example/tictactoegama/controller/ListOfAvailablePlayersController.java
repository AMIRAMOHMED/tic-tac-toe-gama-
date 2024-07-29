/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tictactoegama.controller;


import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.Api.RequestHandler;
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
public class ListOfAvailablePlayersController extends Thread implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    FlowPane onlinePlayers;
    @FXML
    TextField txt;
    @FXML
    FlowPane noplayers;
    @FXML
    SVGPath refresh;

    Text playername;
    SVGPath status;
    Button invite;
    ArrayList<OnlinePlayerListItem> items;
    Client client;
    BufferedReader inp;
    PrintWriter dos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items = new ArrayList<OnlinePlayerListItem>();
            ClientHandler.send("{\"RequestType\":\"PlayerList\"}");
            JSONObject object = new JSONObject(RequestHandler.getResponse());
            JSONArray objarr = object.getJSONArray("PlayList");
            if (!objarr.isEmpty()) {
                noplayers.setOpacity(0);
                for (int i = 0; i < objarr.length(); i++) {
                    JSONObject item = objarr.getJSONObject(i);
                    onlinePlayers.getChildren().add(new OnlinePlayerListItem(item.getString("username"), item.getBoolean("isingame"), item.getInt("userid")));
                }
            }
        refresh.setOnMouseClicked(event -> {
            items = new ArrayList<OnlinePlayerListItem>();
                ClientHandler.send("{\"RequestType\":\"PlayerList\"}");
                JSONObject object2 = new JSONObject(RequestHandler.getResponse());
                JSONArray objarr2 = object2.getJSONArray("PlayList");
                if (!objarr.isEmpty()) {
                    noplayers.setOpacity(0);
                    for (int i = 0; i < objarr.length(); i++) {
                        JSONObject item = objarr.getJSONObject(i);
                        onlinePlayers.getChildren().add(new OnlinePlayerListItem(item.getString("username"), item.getBoolean("isingame"), item.getInt("userid")));
                    }
                }

        });
    }

}
