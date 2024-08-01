/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tictactoegama.controller;

import com.example.tictactoegama.logic.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
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
    private void handleGoBack(ActionEvent event) throws IOException {
        Parent gamePageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/options_page.fxml"));
        Scene gamePageScene = new Scene(gamePageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(gamePageScene);
        window.show();
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

        for(int i =0; i<data.length() ; i++){
            System.out.println(data.get(i));
            JSONObject jsonObject = new JSONObject(data.get(i).toString());
            System.out.println(jsonObject.toString());
            String player1=jsonObject.getString("player1");
            String player2=jsonObject.getString("player2");
            String winStatus="1";

            historyList.getItems().add(new viewListIHistoryBase(player1,player2 ,jsonObject.getInt("win")));
        }
    }    
    
    
}
