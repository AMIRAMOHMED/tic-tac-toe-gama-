package com.example.tictactoegama.models;

import org.json.JSONObject;

public class User {
    private int userid;
    private String username;
    private String email;
    private String password;
    
    public User() {
    }

    public User(int userid, String username, String email, String password) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
    }
   
    @Override
    public String toString() {
        return "{\"userid\":" + userid + ", \"username\":\"" + username + "\", \"email\":\"" + email + "\", \"password\":\"" + password
                + "\"}";
    }

    public void toUser(String query){
        JSONObject user= new JSONObject(query);
        userid = user.getInt("userid");
        username= user.getString(username);
        email= user.getString(email);
        password= user.getString(password);
    }
}
