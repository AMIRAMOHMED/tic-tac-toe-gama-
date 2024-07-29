package com.example.tictactoegama.Api;

import com.example.tictactoegama.constants.RequestType;
import com.example.tictactoegama.controller.requestAlertBoxBase;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONObject;


import static com.example.tictactoegama.constants.RequestType.RequestGame;

public class RequestHandler {
    private static String response;
    public static void setResponse(String response){
        System.out.println("Request handler "+response);
        RequestHandler.response = response;
        showDialog();
    }
    public static String getResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
    private static void showDialog(){
        if (response != null) {
            System.out.println(response);
            JSONObject object = new JSONObject(response);
            RequestType type = RequestType.valueOf(object.getString("RequestType"));
            if (type == RequestGame) {
                Platform.runLater(() -> {
                    Stage window = new Stage();
                    Parent root = new requestAlertBoxBase(object,window);
                    Scene scene = new Scene(root);
                    window.setScene(scene);
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.show();});

            }
        }
    }
}
