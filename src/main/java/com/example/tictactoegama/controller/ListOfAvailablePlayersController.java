/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tictactoegama.controller;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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


    ArrayList<OnlinePlayerListItem> items;
    public static Client client;
    DataOutputStream output;
    DataInputStream input;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items = new ArrayList<OnlinePlayerListItem>();
        try {
            client = Client.getInstance();
            output = new DataOutputStream(client.getSocket().getOutputStream());
            input = new DataInputStream(client.getSocket().getInputStream());
            output.writeUTF("{\"RequestType\":\"PlayerList\"}");
            String response = input.readUTF();
            System.out.println(response);
            JSONObject object = new JSONObject(response);
            JSONArray objarr = object.getJSONArray("PlayList");
            if (!objarr.isEmpty()){
                noplayers.setOpacity(0);
                for (int i = 0 ; i< objarr.length();i++){
                    JSONObject item = objarr.getJSONObject(i);
                    onlinePlayers.getChildren().add(new OnlinePlayerListItem(item.getString("username"), item.getInt("userid"),item.getBoolean("isingame")));
                }
            }

        }  catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
    public static void requestGame(int playerId) {
        try {
            if (client != null && client.isConnected()) {
                Socket socket = client.getSocket();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream inp = new DataInputStream(socket.getInputStream());

                JSONObject request = new JSONObject();
                request.put("RequestType", "RequestGame");
                request.put("PlayerId", playerId);
                System.out.println(request);
                dos.writeUTF(request.toString());
                String result = inp.readUTF();
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
