package com.example.tictactoegama.logic;
import org.json.JSONArray;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileHandler {
    File tempFile;
    public FileHandler(){
        File theDir = new File(System.getProperty("java.io.tmpdir")+"\\tictactoe");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        tempFile = new File(theDir.getAbsolutePath()+"\\gamehistory.txt");
    }
    public JSONArray readFile() throws IOException{
        FileReader file = new FileReader(tempFile);
        BufferedReader input = new BufferedReader(file);
        String line="";
        JSONArray history = new JSONArray();
        while ((line = input.readLine()) !=null){
            history.put(line);
        }
        input.close();
        return history;
    }
    public void writeFile(String query) throws IOException{
        FileWriter file = new FileWriter(tempFile,true);
        BufferedWriter output = new BufferedWriter(file);
        output.write(query+"\n");
        output.close();

    }
}
