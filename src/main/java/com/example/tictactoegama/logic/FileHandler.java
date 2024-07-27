package com.example.tictactoegama.logic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
    File tempFile;
    FileInputStream input;
    FileOutputStream output;
    FileHandler(){
        File theDir = new File(System.getProperty("java.io.tmpdir")+"\\tictactoe");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        tempFile = new File(theDir.getAbsolutePath()+"\\gamehistory.txt");
    }
    public String readFile() throws IOException{
        input = new FileInputStream(tempFile);
        String history = input.readAllBytes().toString();
        return history;
    }
    public void writeFile(String query) throws IOException{
        output = new FileOutputStream(tempFile);
        output.write(query.getBytes());
    }
}
