package com.example.furniture_app.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.furniture_app.R;

import java.util.concurrent.Executors;

@androidx.room.Database(entities = {User.class,Product.class,FavoriteProducts.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract ProductDAO productDAO();
    public abstract FavoriteDAO favoriteDAO();
    public static volatile Database INSTANCE;
    private static final RoomDatabase.Callback DatabaseCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                ProductDAO productDAO = INSTANCE.productDAO();
                productDAO.insert(new Product(R.drawable.yellow_sofa,"Modern Sofas","450$"));
                productDAO.insert(new Product(R.drawable.seater_sofa,"Modern Sofas","450$"));
                productDAO.insert(new Product(R.drawable.table_lamp,"Modern Sofas","450$"));
                productDAO.insert(new Product(R.drawable.modern_velvet,"Modern Sofas","450$"));
            });

        }
    };
    public static Database getINSTANCE(final Context context){
        if(INSTANCE == null){
            synchronized (Database.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, Database.class, "FurnitureDatabase")
                            .allowMainThreadQueries()
                            .addCallback(DatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
