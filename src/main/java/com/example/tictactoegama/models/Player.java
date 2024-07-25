package com.example.tictactoegama.models;

public class Player {
    private int userid;
    private String username;
    private boolean isloggedin;
    private boolean isingame;
    private boolean gamesplayed;
    private int wins;
    private int draws;
    private int losses;
    private int score;
    public Player(int userid, String username, boolean isloggedin, boolean isingame, boolean gamesplayed, int wins,
            int draws, int losses) {
        this.userid = userid;
        this.username = username;
        this.isloggedin = isloggedin;
        this.isingame = isingame;
        this.gamesplayed = gamesplayed;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        score = wins - losses;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public boolean isIsloggedin() {
        return isloggedin;
    }
    public void setIsloggedin(boolean isloggedin) {
        this.isloggedin = isloggedin;
    }
    public boolean isIsingame() {
        return isingame;
    }
    public void setIsingame(boolean isingame) {
        this.isingame = isingame;
    }
    public boolean isGamesplayed() {
        return gamesplayed;
    }
    public void setGamesplayed(boolean gamesplayed) {
        this.gamesplayed = gamesplayed;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public int getDraws() {
        return draws;
    }
    public void setDraws(int draws) {
        this.draws = draws;
    }
    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }
    public void setScore(){
        score = wins-losses;
    }
    public void addLose(){
        losses++;
        score--;
    }
    public void addWin(){
        wins++;
        score++;
    }
    @Override
    public String toString() {
        return "userid=" + userid + ", username=" + username + ", isloggedin=" + isloggedin + ", isingame="
                + isingame + ", gamesplayed=" + gamesplayed + ", wins=" + wins + ", draws=" + draws + ", losses="
                + losses + ", score=" + score ;
    }

    public void toPlayer(){
        
    }
    

    
    
}
