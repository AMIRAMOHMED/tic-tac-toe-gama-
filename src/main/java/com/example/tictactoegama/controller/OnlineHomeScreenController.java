package com.example.tictactoegama.controller;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.Api.RequestHandler;
import com.example.tictactoegama.TicTacToeGama;
import com.example.tictactoegama.models.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OnlineHomeScreenController implements Initializable {
    @FXML
    FlowPane gameHistory;
    @FXML
    FlowPane scoreBoard;
    @FXML
    Text name;
    @FXML
    Text wins;
    @FXML
    Text losses;
    @FXML
    Text draws;
    @FXML
    SVGPath listofplayers;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(Client.user.getUsername());
        wins.setText(""+Client.user.getWins());
        losses.setText(""+Client.user.getLosses());
        draws.setText(""+Client.user.getDraws());
        listofplayers.setOnMouseClicked((event)->{
            Parent optionPageParent = null;
            try {
                optionPageParent = FXMLLoader.load(RequestHandler.class.getResource("/com/example/tictactoegama/views/ListOfAvailablePlayers.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene optionPageScene = new Scene(optionPageParent);
            Stage window = TicTacToeGama.getStage();
            window.setScene(optionPageScene);
        });
        gameHistory.setOnMouseClicked((event -> {
            Parent optionPageParent = null;
            try {
                optionPageParent = FXMLLoader.load(RequestHandler.class.getResource("/com/example/tictactoegama/views/HistoryPage.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene optionPageScene = new Scene(optionPageParent);
            Stage window = TicTacToeGama.getStage();
            window.setScene(optionPageScene);
        }));
        scoreBoard.setOnMouseClicked(event -> {
            Parent optionPageParent = null;
            try {
                optionPageParent = FXMLLoader.load(RequestHandler.class.getResource("/com/example/tictactoegama/views/Scoreboard.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene optionPageScene = new Scene(optionPageParent);
            Stage window = TicTacToeGama.getStage();
            window.setScene(optionPageScene);
        });
    }
}
