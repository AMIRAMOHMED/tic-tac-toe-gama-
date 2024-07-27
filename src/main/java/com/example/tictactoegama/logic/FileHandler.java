package com.example.tictactoegama.logic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
    File file;
    FileInputStream input;
    FileOutputStream output;
    FileHandler(){
        try {
            file = File.createTempFile("gamelogs", "offline");
        } catch (IOException e) {
            System.out.println("File Not found!");
            e.printStackTrace();
        }
    }
}
