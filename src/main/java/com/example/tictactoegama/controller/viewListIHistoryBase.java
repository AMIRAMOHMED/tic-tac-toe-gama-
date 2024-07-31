package com.example.tictactoegama.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class viewListIHistoryBase extends AnchorPane {

    protected final Label playerNameLable;
    protected final Label winLoseLable;
    protected final Label label;
    protected final Button viewGameBtn;
    
    public viewListIHistoryBase(String playerName ,String date, int winOrLose ) {

        playerNameLable = new Label();
        winLoseLable = new Label();
        label = new Label();
        viewGameBtn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(55.0);
        setPrefWidth(440.0);

        playerNameLable.setLayoutX(29.0);
        playerNameLable.setLayoutY(6.0);
        playerNameLable.setStyle("-fx-font-size: 18; -fx-font-family: 'Roboto', sans-serif; -fx-font-weight: 400;");
        playerNameLable.setText(playerName);

        winLoseLable.setLayoutX(392.0);
        winLoseLable.setLayoutY(14.0);
        if(winOrLose==0){
            winLoseLable.setStyle("-fx-font-family: 'Roboto', sans-serif; -fx-font-size: 14; -fx-font-weight: 500; -fx-text-fill: #FF827E;");
            winLoseLable.setText("LOSE");
        }else if(winOrLose==1){
            winLoseLable.setStyle("-fx-font-family: 'Roboto', sans-serif; -fx-font-size: 14; -fx-font-weight: 500; -fx-text-fill: #00C096;");
            winLoseLable.setText("WON");
        }else{
            winLoseLable.setStyle("-fx-font-family: 'Roboto', sans-serif; -fx-font-size: 14; -fx-font-weight: 500; -fx-text-fill: #ADADAD;");
            winLoseLable.setText("DRAW");
        }
        
        viewGameBtn.setLayoutX(226.0);
        viewGameBtn.setLayoutY(12.0);
        viewGameBtn.setMnemonicParsing(false);
        viewGameBtn.setOnAction(this::handleViewGame);
        viewGameBtn.setStyle("-fx-font-family: 'Roboto', sans-serif; -fx-font-weight: 400; -fx-text-fill: #FFFFFF; -fx-background-color: #46A3FF;");
        viewGameBtn.setText("View Game");
        
        label.setLayoutX(33.0);
        label.setLayoutY(31.0);
        label.setPrefHeight(18.0);
        label.setPrefWidth(106.0);
        label.setStyle("-fx-font-family: 'Roboto', sans-serif; -fx-font-size: 14; -fx-font-weight: 400; -fx-text-fill: #ADADAD;");
        label.setText(date);

        getChildren().add(playerNameLable);
        getChildren().add(winLoseLable);
        getChildren().add(label);
        getChildren().add(viewGameBtn);
        
    }
    public void handleViewGame(ActionEvent event){
            
    }
}
