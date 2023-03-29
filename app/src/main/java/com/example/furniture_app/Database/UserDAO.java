package com.example.furniture_app.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);
    @Delete
    void deleteUser(User user);
    @Update
    void updateUserData(User user);
    @Query("SELECT * FROM User WHERE email=:email")
    User getUser(String email);
}
