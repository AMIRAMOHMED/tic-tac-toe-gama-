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
import java.util.Vector;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.Api.RequestHandler;
import com.example.tictactoegama.models.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    @FXML
    SVGPath refresh;

    public static Thread th;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ClientHandler.send("{\"RequestType\":\"PlayerList\"}");
                Platform.runLater(()->{
                    Vector<Player> players = RequestHandler.getPlayerList();
                        noplayers.setOpacity(0);
                        for (int i = 0; i < players.size(); i++) {
                            onlinePlayers.getChildren().add(new OnlinePlayerListItem(players.get(i)));
                    }
                });
               th = new Thread(()->{
                    while (true) {
                        try {
                            Thread.sleep(1000);
                            Platform.runLater(() -> {
                                onlinePlayers.getChildren().clear();
                                Vector<Player> players = RequestHandler.getPlayerList();
                                if (players.isEmpty()) {
                                    noplayers.setOpacity(1);
                                } else {
                                    noplayers.setOpacity(0);
                                    for (int i = 0; i < players.size(); i++) {
                                        onlinePlayers.getChildren().add(new OnlinePlayerListItem(players.get(i)));
                                    }
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
               th.start();
        refresh.setOnMouseClicked(event -> {
            Platform.runLater(()->{
                Vector<Player> players = RequestHandler.getPlayerList();
                noplayers.setOpacity(0);
                for (int i = 0; i < players.size(); i++) {
                    onlinePlayers.getChildren().add(new OnlinePlayerListItem(players.get(i)));
                }
            });

        });
    }
}
