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
    static Client client;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items = new ArrayList<OnlinePlayerListItem>();
        try {
            Socket socket = client.getSocket();
            PrintWriter dos = new PrintWriter(socket.getOutputStream());
            dos.println("{\"RequestType\":\"PlayerList\"}");
            dos.flush();
            BufferedReader inp = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            JSONObject object = new JSONObject(inp.readLine());
            inp.close();
            dos.close();
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
        }
    }
}
