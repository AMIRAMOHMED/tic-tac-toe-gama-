import java.util.ArrayList;

public class GameMoves {
    private String player1;
    private String player2;
    private ArrayList<Integer> moves;
    public GameMoves(String player1, String player2, ArrayList moves) {
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
    public ArrayList<> getMoves() {
        return moves;
    }
    public void setMoves(ArrayList<> moves) {
        this.moves = moves;
    }
    @Override
    public String toString() {
        String gamemoves = "";
        for(int i =0;i<moves.size();i++){
            gamemoves = moves.get(i) + ",";
        }
        return "GameMoves [player1=" + player1 + ", player2=" + player2 + ", moves=" + gamemoves + "]";
    }
    
}
