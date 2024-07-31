package com.example.tictactoegama.controller;

import com.example.tictactoegama.interfaces.AIMoodOption;
import com.example.tictactoegama.logic.AIEasyMode;
import com.example.tictactoegama.logic.AIHardMode;
import com.example.tictactoegama.logic.MediumMood;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LevelController {

    @FXML
    private Button easyButton;

    @FXML
    private Button mediumButton;

    @FXML
    private Button hardButton;

    @FXML
    private void initialize() {
        easyButton.setOnAction(e -> handleButtonActionChooseLevel(e, new AIEasyMode()));
        mediumButton.setOnAction(e -> handleButtonActionChooseLevel(e, new MediumMood()));
        hardButton.setOnAction(e -> handleButtonActionChooseLevel(e, new AIHardMode()));
    }
    public void handleGoBack(ActionEvent event) throws IOException {
        Parent optionPageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/options_page.fxml"));
        Scene optionPageScene = new Scene(optionPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(optionPageScene);
        window.show();
    }

    private void handleButtonActionChooseLevel(ActionEvent event, AIMoodOption aiMoodOption) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tictactoegama/views/gama-page.fxml"));
            Parent gamePageParent = loader.load();

            GameController gameController = loader.getController();
            gameController.setAiMoodOption(aiMoodOption);

            Scene gamePageScene = new Scene(gamePageParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(gamePageScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void goToHistory(ActionEvent event) throws IOException {
        Parent optionPageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/HistoryPage.fxml"));
        Scene optionPageScene = new Scene(optionPageParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(optionPageScene);
        window.show();
    }
}
