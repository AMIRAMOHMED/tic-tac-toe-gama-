package com.example.tictactoegama.Api;

import com.example.tictactoegama.constants.RequestType;
import com.example.tictactoegama.controller.*;
import com.example.tictactoegama.logic.MediumMood;
import com.example.tictactoegama.logic.OnlineGamePlay;
import com.example.tictactoegama.models.Player;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class RequestHandler {
    private static Vector<Player> playerList;
    private static Vector<Player> opponentList = new Vector<>();
    private static HashMap<RequestType, Long> lastUpdate = new HashMap<>();
    public static void getResponse(String response){
        JSONObject object = new JSONObject(response);
        RequestType type = RequestType.valueOf(object.getString("RequestType"));
        lastUpdate.put(type,System.currentTimeMillis());
        switch (type) {
            case Register:
                getRegister(object);
                break;
            case Login:
                getLogin(object);
                break;
            case RequestGame:
                requestGameDialog(object);
                break;
            case RequestGameResponse:
                getRequestGameResponse(object);
                break;
            case PlayAgain:
                break;
            case Surrender:
                break;
            case PlayerList :
                try {
                    playerList = new Vector<>();
                    JSONArray list = object.getJSONArray("PlayList");
                    for (int i = 0; i<list.length();i++){
                        playerList.add(Player.fromJson(list.getJSONObject(i)));
                    }
                    System.out.println(playerList);
                } catch (Exception e ){
                    e.printStackTrace();
                }
                break;
            case Scoreboard:
                break;
            case GameHistory:
                break;
            case Ignore:
                break;
        };
    }
    private static synchronized void getRegister(JSONObject object){
        if (object.getInt("value")==11){
            RegisterController register = new RegisterController();
            register.goToLoginPage();
        }
        else{
            showAlert("Error","Try Again");
        }
    }
    private static synchronized void getLogin(JSONObject object) {
        Platform.runLater(()->{
            try {
                if (object.has("userid")) {
                    int id =object.getInt("userid");
                    Client.userid = id;
                    LoginController login = new LoginController();
                    login.getHomePage();

                } else if (object.getString("invalid").equals("Invalid username or password")) {
                    showAlert("Error", "Invalid username or password");

                }else {
                    showAlert("Error","Failed to update login status");
                } } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to communicate with server.");
            }
        });
    }

    private static synchronized void getRequestGameResponse(JSONObject object){
        if (object.getBoolean("Value")){
            initOnlineGame();
        }
    }

    private static void showAlert(String title, String message) {
        // Shows an alert with the provided title and message
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
    public static Vector<Player> getPlayerList() {
        return playerList;
    }
        private static void requestGameDialog(JSONObject object){
                Platform.runLater(() -> {
                    Stage window = new Stage();
                    Parent root = new requestAlertBoxBase(object,window);
                    Scene scene = new Scene(root);
                    window.setScene(scene);
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.show();});
    }

    public static void initOnlineGame() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(RequestHandler.class.getResource("/com/example/tictactoegama/views/gama-page.fxml"));
            Parent gamePageParent = null;
            try {
                gamePageParent = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GameController onlineGameController = loader.getController();
            onlineGameController.setAiMoodOption(new OnlineGamePlay());
            Scene gamePageScene = new Scene(gamePageParent);
            Stage window = new Stage();
            window.setScene(gamePageScene);
            window.show();
        });
    }

}


