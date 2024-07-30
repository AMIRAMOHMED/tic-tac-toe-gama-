package com.example.tictactoegama.Api;

import java.io.IOException;
import java.net.Socket;

public class Client{

    public  static  int userid;
    private static Socket socket;
    private static Client client;
    private static String ip;
    private boolean isConnected;
    private static int port;
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
            new ClientHandler(socket);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    private Client(String IP) {
        this(IP, 5005);
    }

    public boolean isConnected() {
        return isConnected;
    }
}