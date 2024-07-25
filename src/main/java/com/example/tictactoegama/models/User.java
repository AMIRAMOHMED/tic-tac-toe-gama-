package com.example.tictactoegama.models;

public class User {
    private int userid;
    private String username;
    private String email;
    private String password;
    public User(int userid, String username, String email, String password) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @Override
    public String toString() {
        return "userid=" + userid + ", username=" + username + ", email=" + email + ", password=" + password
                ;
    }
    public void toUser(String query){
        String[] splitted= query.split(",");
        userid = Integer.parseInt(splitted[0].substring(splitted[0].indexOf("="),splitted[0].length()));
        username = splitted[1].substring(splitted[1].indexOf("="),splitted[1].length());
        email = splitted[2].substring(splitted[2].indexOf("="),splitted[2].length());
        password = splitted[3].substring(splitted[3].indexOf("="),splitted[3].length());
    }
}
