package com.example.tictactoegama.logic;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import com.example.tictactoegama.Api.RequestHandler;
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
            int i = Integer.parseInt("RequestHandler.getResponse()");
            if (i==10){
                return 1;
            }
            return board.play(i, opponentSymbol);
    }

}
