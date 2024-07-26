package com.example.tictactoegama.models;

import com.example.tictactoegama.controller.VideoController;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class VideoViewHandler {
    public void showVideoView(Stage stage, String videoPath) {
        try {
            // Load the video view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tictactoegama/views/Video.fxml"));
            Parent videoRoot = loader.load();
            Scene videoScene = new Scene(videoRoot);

            // Get the VideoController and set the stage, previous scene, and video path
            VideoController videoController = loader.getController();
            videoController.setVideoPath(videoPath);

            // Set the video scene
            stage.setScene(videoScene);
            stage.setOnCloseRequest(event -> videoController.closeVid());
            // Pause for 10 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(event -> {
                // Switch back to the original game scene
                stage.close();;
            });
            pause.play();

        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions
        }
    }
}