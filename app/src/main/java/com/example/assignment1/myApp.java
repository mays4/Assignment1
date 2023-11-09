package com.example.assignment1;

import android.app.Application;

import java.util.ArrayList;

public class myApp extends Application {
    public ArrayList<String> getListOfHistory() {
        if(listOfHistory == null){
           listOfHistory = new ArrayList<>(0);
        }
        return listOfHistory;
    }


    ArrayList<String> listOfHistory;

    int index = 0;


}
