package com.example.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM User ORDER BY name ASC")
    LiveData<List<User>> sortByAsc();

    @Insert
    void insert(User user);

    @Delete
    void remove(User user);

}
