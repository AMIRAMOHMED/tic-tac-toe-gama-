package com.example.tictactoegama.Api;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket socket;
    private boolean isConnected;

    public Client(String IP, int port) {
        try {
            socket = new Socket(IP, port);
            System.out.println("Connected to server at " + IP + " on port " + port);
            isConnected = true;
            // Create a new thread to handle the client's requests
            GeneralRequestHandler requestHandler = new GeneralRequestHandler(socket);
            Thread thread = new Thread(requestHandler);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
            isConnected = false;
        }
    }

    public Client(String IP) {
        this(IP, 5005);
    }

    public boolean isConnected() {
        return isConnected;
    }
}
