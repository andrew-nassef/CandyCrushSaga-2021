package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Players extends Users{


    private int current_score;
    FileWriter fileWriter = null;
    BufferedWriter bufferWriter = null;
    PrintWriter printWriter = null;


    public void add_player(String n, int s){
        current_username =n;
        current_score=s;

        try {
            fileWriter = new FileWriter("players.txt", true);
            bufferWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferWriter);
            printWriter.println(current_username);
            printWriter.println(current_score);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {

            try {
                if(bufferWriter != null)
                    bufferWriter.close();
            } catch (IOException e) {

            }
            try {
                if(fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {

            }
        }



    }




    public String getCurrent_username() {
        return current_username;
    }


    public void setCurrent_username(String current_username) {
        this.current_username = current_username;
    }

    public int getCurrent_score() {
        return current_score;
    }

    public void setCurrent_score(int current_score) {
        this.current_score = current_score;
    }

    @Override
    public String toString() {
        return "Score{" + "current_username=" + current_username + ", current_score=" + current_score + '}';
    }



}
