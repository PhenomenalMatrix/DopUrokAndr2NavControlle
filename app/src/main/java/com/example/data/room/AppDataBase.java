package com.example.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
}
