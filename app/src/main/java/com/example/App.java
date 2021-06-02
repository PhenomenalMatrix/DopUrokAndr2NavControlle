package com.example;

import android.app.Application;

import androidx.room.Room;

import com.example.data.room.AppDataBase;

public class App extends Application {

    private static AppDataBase dataBase;
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataBase = Room.databaseBuilder(this,AppDataBase.class,"database")
                .allowMainThreadQueries()
                .build();
    }

    public static AppDataBase getDataBase() {
        return dataBase;
    }

    public static App getInstance() {
        return instance;
    }
}
