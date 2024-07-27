package com.example.tictactoegama.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 *
 * @author Mohamed Fekry Khedr
 */
public class Scoreboard implements Initializable {
    
    @FXML
    ListView<xo.viewListIScoreboardBase> nameList;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i=1;i<20;i++){
            nameList.getItems().add(new xo.viewListIScoreboardBase(i+". khaled", 255));
        }
    }    
    
    
}
