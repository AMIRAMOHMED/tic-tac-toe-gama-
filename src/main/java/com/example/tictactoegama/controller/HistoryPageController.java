/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tictactoegama.controller;

import com.example.tictactoegama.logic.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


/**
 *
 * @author Mohamed Fekry Khedr
 */
public class HistoryPageController implements Initializable {
    
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<viewListIHistoryBase> historyList;
    @FXML
    Button backBtn;
    
    
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        
     
    }
    @FXML
    private void handleDateBicker(ActionEvent event) throws IOException {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FileHandler fileHandler = new FileHandler();
        JSONArray data;
        try {
            data = fileHandler.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        String[] arr = data.split("\n");

        for(int i =0; i<data.length() ; i++){
            JSONObject jsonObject = new JSONObject(data.get(i));
            String player1=jsonObject.getString("player1");
            String player2=jsonObject.getString("player2");
            String winStatus=jsonObject.getString("win");

            historyList.getItems().add(new viewListIHistoryBase(player1,player2 ,winStatus=="1"?1:0));
        }
    }    
    
    
}
