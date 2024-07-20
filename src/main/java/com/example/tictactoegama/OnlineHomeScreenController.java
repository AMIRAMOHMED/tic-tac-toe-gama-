/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author filop
 */
public class OnlineHomeScreenController implements Initializable {
    
    @FXML 
    AnchorPane mainpane;
    @FXML
    Text playerName;
    @FXML
    Text wins;
    @FXML
    Text losses;
    @FXML
    Text draws;
    @FXML
    HBox gameHistoryRow;
    @FXML
    VBox gameDetailsVbox;
    @FXML
    Text opponentName;
    @FXML
    Text gameStatus;
    @FXML
    Text gameDate;
    Map<String,String> gameHistory;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
