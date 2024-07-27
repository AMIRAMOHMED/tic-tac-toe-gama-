package com.example.tictactoegama.Api;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket socket;

    public Client(String IP, int port) {
        try {
            socket = new Socket(IP, port);
            System.out.println("Connected to server at " + IP + " on port " + port);

            // Create a new thread to handle the client's requests
            GeneralRequestHandler requestHandler = new GeneralRequestHandler(socket);
            Thread thread = new Thread(requestHandler);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 public Client (){

        String serverIP = "10.10.13.101";

        new Client(serverIP, 5005);
    }
}
