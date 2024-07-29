package com.example.tictactoegama.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private boolean isConnected;
    private static Client client;
    private PrintWriter sender;
    private BufferedReader reader;
    private static String ip;
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
    public static DataInputStream getInput() {
        return input;
    }
    public static DataOutputStream getOutput() {
        return output;
    }
}
