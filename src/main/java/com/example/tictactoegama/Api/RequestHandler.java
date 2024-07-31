package com.example.tictactoegama.Api;

import com.example.tictactoegama.TicTacToeGama;
import com.example.tictactoegama.constants.RequestType;
import com.example.tictactoegama.controller.*;
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
    private static boolean isgameended = false;
    public static int index = -1;
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
            case InGame:
                index = (object.getInt("play"));
                break;
            case RequestGame:
                requestGameDialog(object);
                break;
            case RequestGameResponse:
                getRequestGameResponse(object);
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
            case GameEnded:
                setGameEnded(true);
                break;
        };
    }


    public static boolean getGameEnded(){
        return isgameended;
    }
    private static void setGameEnded(boolean isended) {
        isgameended =  isended;
    }

    public static void setIndex(int i){
        index=i;
    }
    public synchronized static int getPlay(){
        return index;
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
                if (object.has("Player")) {
                    Client.user = Player.fromJson(object.getJSONObject("Player"));
                    System.out.println("here");
                    System.out.println(Client.user);
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
            initOnlineGame(object);
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

    public synchronized static void initOnlineGame(JSONObject object) {
        Platform.runLater(() -> {
            try {
            FXMLLoader loader = new FXMLLoader(RequestHandler.class.getResource("/com/example/tictactoegama/views/OnlineGameUI.fxml"));
            Parent gamePageParent = loader.load();
            Scene gamePageScene = new Scene(gamePageParent);
                Stage window = TicTacToeGama.getStage();
                window.setScene(gamePageScene);
                Player opponent =  Player.fromJson(object.getJSONObject("opponent"));
                System.out.println("Here");
                System.out.println(opponent);
                System.out.println(Client.user);
                System.out.println("---------------");
                boolean flag = object.getBoolean("flag");
                if (flag)
                    OnlineGameController.setPlayers(flag, Client.user , opponent);
                else
                    OnlineGameController.setPlayers(flag, opponent , Client.user);
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}


