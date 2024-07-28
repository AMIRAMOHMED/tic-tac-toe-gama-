package com.example.tictactoegama.logic;
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
    public String readFile() throws IOException{
        FileReader file = new FileReader(tempFile);
        BufferedReader input = new BufferedReader(file);
        String history="";
        String newhistory;
        while ((newhistory = input.readLine()) !=null){
            newhistory += "\n"+ newhistory;
        }
        System.out.println(newhistory);
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
