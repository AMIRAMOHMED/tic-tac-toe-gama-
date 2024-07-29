package com.example.tictactoegama.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler{
    private Socket socket;
    private static BufferedReader reader;
    private static PrintWriter sender;
    public ClientHandler(Socket socket) {
        this.socket = socket;
        applySender();
        applyReader();
        listen();
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
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    private void listen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        RequestHandler.setResponse(line);
                    }
                }  catch (IOException e) {
                    e.printStackTrace();
                }
                disconnect();
            }
        }).start();
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
