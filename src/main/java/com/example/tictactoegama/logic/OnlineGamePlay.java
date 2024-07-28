package com.example.tictactoegama.logic;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.interfaces.AIMoodOption;
import com.example.tictactoegama.models.PlayBoard;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class OnlineGamePlay implements AIMoodOption {
    char opponentSymbol;
    @Override
    public int makeMove(PlayBoard board, char opponentSymbol) {
        try {
            Client client = Client.getInstance();
            DataInputStream input = new DataInputStream(client.getSocket().getInputStream());
            int i = input.readInt();
            if (i==10){
                return 1;
            }
            return board.play(i, opponentSymbol);
        } catch (IOException e) {
            return 0;
        } catch (InstantiationException e) {
            return 0;
        }
    }

}
