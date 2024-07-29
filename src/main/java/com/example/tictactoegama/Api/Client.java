package com.example.tictactoegama.Api;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client{

    public  static  int userid;
    private Socket socket;
    private boolean isConnected;
    private static Client client;
    private static String ip;
    private static int port;
    private BufferedReader input;
    private PrintWriter output;
    public static Client getInstance() throws InstantiationException
    {
        if(client == null)
        {
            throw new InstantiationException("You have to call init function first.");
        }

        return client;
    }

    public static void init(String ip, int port)
    {
        client = new Client(ip,port);
        Client.ip=ip;
        Client.port=port;
    }
    private  Client(String IP, int port) {
        try {
            socket = new Socket(IP, port);
            System.out.println("Connected to server at " + IP + " on port " + port);
            isConnected = true;
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            isConnected = false;
        }
    }

    private Client(String IP) {
        this(IP, 5005);
    }

    public boolean isConnected() {
        return isConnected;
    }
    public Socket getSocket() throws IOException {
        if (socket.isClosed())
            socket=new Socket(Client.ip,Client.port);
        return socket;
    }

}