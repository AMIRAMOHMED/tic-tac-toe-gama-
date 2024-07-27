package com.example.tictactoegama.Api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GeneralRequestHandler implements Runnable {
    private Socket clientSocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public GeneralRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Handle client requests
            String request = dataInputStream.readUTF();
            System.out.println("Received request: " + request);

            // Process the request and generate a response
            String response = processRequest(request);
            dataOutputStream.writeUTF(response);
            dataOutputStream.flush();

            // Close streams and socket
            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processRequest(String request) {
        // Add logic to process the request here
        // For example, handle different types of requests and return appropriate responses
        return "Response to: " + request;
    }
}
