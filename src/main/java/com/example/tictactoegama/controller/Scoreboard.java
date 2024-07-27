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

import com.example.tictactoegama.Api.Client;
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
    ListView<xo.viewListIScoreboardBase> nameList;

    Client client;
    DataInputStream input;
    DataOutputStream output;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            client = Client.getInstance();
            output = new DataOutputStream(client.getSocket().getOutputStream());
            input = new DataInputStream(client.getSocket().getInputStream());
            output.writeUTF("{\"RequestType\":\"Scoreboard\"}");
            JSONObject object = new JSONObject(input.readUTF());
            JSONArray objarr = object.getJSONArray("Scoreboard");
            if (!objarr.isEmpty()){
                for (int i = 0 ; i< objarr.length();i++){
                    JSONObject item = objarr.getJSONObject(i);
                    nameList.getItems().add(new xo.viewListIScoreboardBase(i+". "+ item.getString("username"), item.getInt("score")));
                }
            }


        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }    
    
    
}
