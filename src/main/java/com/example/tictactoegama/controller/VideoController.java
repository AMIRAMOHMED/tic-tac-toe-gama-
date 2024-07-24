/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tictactoegama.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Mohamed Fekry Khedr
 */
public class VideoController implements Initializable {
    
    @FXML
    private MediaView myMediaView;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        file = new File("D:\\ITI\\java\\tasks\\xo\\filo-test.mp4");
        media = new Media(file.toURI().toString());
        
        mediaPlayer = new MediaPlayer(media);
        
        myMediaView.setMediaPlayer(mediaPlayer);
        //myMediaView.setFitWidth(media.getWidth());
        
        mediaPlayer.play();
    }    
    
}
