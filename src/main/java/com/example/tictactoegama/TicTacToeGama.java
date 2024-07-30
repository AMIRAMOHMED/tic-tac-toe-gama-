package com.example.tictactoegama;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.controller.ListOfAvailablePlayersController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeGama extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TicTacToeGama.class.getResource("/com/example/tictactoegama/views/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(
            false
        );
        stage.setOnCloseRequest(event -> {
            if (ClientHandler.th != null && ClientHandler.th.isAlive()){
                ClientHandler.th.stop();
                if (ListOfAvailablePlayersController.th != null && ListOfAvailablePlayersController.th.isAlive()){
                    ListOfAvailablePlayersController.th.stop();
                }
            }
        });
    }
    public static void main(String[] args) {
        launch();

    }
}
