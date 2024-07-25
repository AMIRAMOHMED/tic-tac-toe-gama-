package com.example.tictactoegama.models;
import java.util.ArrayList;

public class GameMoves {
    private String player1;
    private String player2;
    private ArrayList<Integer> moves;
    public GameMoves() {
    }
    public GameMoves(String player1, String player2, ArrayList<Integer> moves) {
        this.player1 = player1;
        this.player2 = player2;
        this.moves = moves;
    }
    public String getPlayer1() {
        return player1;
    }
    public void setPlayer1(String player1) {
        this.player1 = player1;
    }
    public String getPlayer2() {
        return player2;
    }
    public void setPlayer2(String player2) {
        this.player2 = player2;
    }
    public ArrayList<Integer> getMoves() {
        return moves;
    }
    public void setMoves(ArrayList<Integer> moves) {
        this.moves = moves;
    }
    @Override
    public String toString() {
        String gamemoves = "";
        for(int i =0;i<moves.size();i++){
            gamemoves += ""+ moves.get(i) + ",";
        } 
        return "player1=" + player1 + ",player2=" + player2 + "," + gamemoves + "";
    }

    public void toGameMoves(String query){
        String[] splitted = query.split(",");
        for(String i : splitted){
            System.out.println(i);
        }
        this.player1 = splitted[0].substring(splitted[0].indexOf("=")+1,splitted[0].length());
        this.player2 = splitted[1].substring(splitted[1].indexOf("=")+1,splitted[1].length());
        ArrayList<Integer> amoves = new ArrayList<Integer>();
        for(int i = 2 ; i<splitted.length;i++){
            amoves.add(Integer.parseInt(splitted[i]));
        }
        moves = amoves;
    }
}
