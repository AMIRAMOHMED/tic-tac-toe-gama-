/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 *
 * @author Mohamed Fekry Khedr
 */
public class Scoreboard implements Initializable {
    
    @FXML
    ListView<viewListIScoreboardBase> nameList;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i=1;i<20;i++){
            nameList.getItems().add(new viewListIScoreboardBase(i+". khaled", 255));
        }
    }    
    
    
}
