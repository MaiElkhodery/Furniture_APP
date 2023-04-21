package com.example.furniture_app.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.furniture_app.FurnitureCategory;
import com.example.furniture_app.R;

import java.util.concurrent.Executors;

@androidx.room.Database(entities = {User.class,Product.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract ProductDAO productDAO();
    public static volatile Database INSTANCE;
    private static final RoomDatabase.Callback DatabaseCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                ProductDAO productDAO = INSTANCE.productDAO();
                productDAO.insert(new Product(R.drawable.yellow_sofa,"Modern Sofas",450, FurnitureCategory.SOFA.getName()));
                productDAO.insert(new Product(R.drawable.seater_sofa,"Modern Sofas",850,FurnitureCategory.SOFA.getName()));
                productDAO.insert(new Product(R.drawable.table_lamp,"Modern Table Lamp",490,FurnitureCategory.LAMP.getName()));
                productDAO.insert(new Product(R.drawable.modern_velvet,"Modern Chair",390,FurnitureCategory.CHAIR.getName()));
                productDAO.insert(new Product(R.drawable.bed1,"Classic Bed",1150,FurnitureCategory.BED.getName()));
                productDAO.insert(new Product(R.drawable.lounge_chair,"Modern Chair",610,FurnitureCategory.CHAIR.getName()));
                productDAO.insert(new Product(R.drawable.modern_side_table,"Round Table",370,FurnitureCategory.TABLE.getName()));
                productDAO.insert(new Product(R.drawable.modern_sofa,"Modern Chair",965,FurnitureCategory.CHAIR.getName()));
                productDAO.insert(new Product(R.drawable.origami_table,"Origami Table",570,FurnitureCategory.TABLE.getName()));
                productDAO.insert(new Product(R.drawable.round_table,"Round Table",880,FurnitureCategory.TABLE.getName()));
                productDAO.insert(new Product(R.drawable.round_table2,"Round Table",600,FurnitureCategory.TABLE.getName()));
                productDAO.insert(new Product(R.drawable.sofa,"Modern Sofas",730,FurnitureCategory.SOFA.getName()));
                productDAO.insert(new Product(R.drawable.swoon_chair,"Modern Chair",180,FurnitureCategory.CHAIR.getName()));
                productDAO.insert(new Product(R.drawable.twist_coffe_table,"Modern Coffee Table",230,FurnitureCategory.TABLE.getName()));
                productDAO.insert(new Product(R.drawable.twist_coffe_table2,"Modern Coffee Table",255,FurnitureCategory.TABLE.getName()));
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
