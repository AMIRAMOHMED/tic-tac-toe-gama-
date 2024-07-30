package tictactoegametests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.example.tictactoegama.Api.Client;
import com.example.tictactoegama.Api.ClientHandler;
import org.junit.jupiter.api.Test;

import com.example.tictactoegama.models.GameMoves;

public class GameMovesTests {
    GameMoves gameMoves;

    @Test
    public void toStringTest(){
        ArrayList<Integer> moves = new ArrayList<Integer>();
        for(int i =0;i<9;i++) moves.add(i);
        gameMoves = new GameMoves("omar","kiro", moves);
        assertEquals(gameMoves.toString(),"player1=omar,player2=kiro,0,1,2,3,4,5,6,7,8,");
    }

    @Test
    public void toGameMovesTest(){
        gameMoves = new GameMoves();
        String query = "player1=omar, player2=kiro,0,1,2,3,4,5,6,7,8,";
        gameMoves.toGameMoves(query);
        assertEquals(gameMoves.getPlayer1(), "omar");
        assertEquals(gameMoves.getPlayer2(), "kiro");
        List<Integer> emoves = new ArrayList<Integer>();
        for(int i =0;i<9;i++) emoves.add(i);
        assertEquals(gameMoves.getMoves(), emoves);
    }
    @Test
    public void PlayListTest(){
        Client.init("localhost",5005);
        ClientHandler.send("{\"RequestType\":\"PlayerList\"}");
    }
}
