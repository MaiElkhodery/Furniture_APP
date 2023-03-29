package com.example.furniture_app.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {User.class,Product.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract ProductDAO productDAO();
    public static volatile Database INSTANCE;
    public static Database getINSTANCE(final Context context){
        if(INSTANCE == null){
            synchronized (Database.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, Database.class, "FurnitureDatabase")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
