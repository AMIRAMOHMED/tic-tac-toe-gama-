package com.example.tictactoegama.Api;

import com.example.tictactoegama.TicTacToeGama;
import com.example.tictactoegama.models.Player;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler{
    public static Socket socket;
    private static BufferedReader reader;
    private static PrintWriter sender;
    public static Thread th;
    public ClientHandler(Socket socket) {
        ClientHandler.socket = socket;
        applySender();
        applyReader();
        try {
            listen();
        } catch (InterruptedException e) {
            disconnect();
        }
    }

    public static void send(Object data) {
        sender.println(data);
        sender.flush();
    }

    private void disconnect() {
        try {
            closeReader();
            closeSender();
            socket.close();
            Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(RequestHandler.class.getResource("/com/example/tictactoegama/views/hello-view.fxml"));
                Parent gamePageParent = null;
                try {
                    gamePageParent = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene gamePageScene = new Scene(gamePageParent);
            Stage window = TicTacToeGama.getStage();
            window.setScene(gamePageScene);
            window.show();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void listen() throws InterruptedException {
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        RequestHandler.getResponse(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                disconnect();
            }
        });
        th.start();
    }

    private void applySender() {
        try {
            sender = (new PrintWriter(socket.getOutputStream(), true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyReader() {
        try {
            reader = (new BufferedReader(new InputStreamReader(socket.getInputStream())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeReader() {
        try {
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeSender() {
        try {
            sender.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
