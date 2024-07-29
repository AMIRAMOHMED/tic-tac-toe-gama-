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
    BufferedReader inp;
    PrintWriter dos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items = new ArrayList<OnlinePlayerListItem>();
        try {
            client = Client.getInstance();
            inp = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
            dos = new PrintWriter(client.getSocket().getOutputStream(),true);
            dos.println("{\"RequestType\":\"PlayerList\"}");
            dos.flush();
            JSONObject object = new JSONObject(inp.readLine());
            JSONArray objarr = object.getJSONArray("PlayList");
            if (!objarr.isEmpty()){
                noplayers.setOpacity(0);
                for (int i = 0 ; i< objarr.length();i++){
                    JSONObject item = objarr.getJSONObject(i);
                    onlinePlayers.getChildren().add(new OnlinePlayerListItem(item.getString("username"), item.getBoolean("isingame"),item.getInt("userid")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> {
            String line;
            try {
                client = Client.getInstance();

            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
                while (true){
                    try {
                        if ((line = inp.readLine()) != null) System.out.println(line);;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

}
