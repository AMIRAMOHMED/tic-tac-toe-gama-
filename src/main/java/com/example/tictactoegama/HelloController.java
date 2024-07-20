package com.example.tictactoegama;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Parent optionPageParent = FXMLLoader.load(getClass().getResource("/com/example/tictactoegama/views/options_page.fxml"));

        Scene optionPageScene = new Scene(optionPageParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(optionPageScene);
        window.show();
    }
}
