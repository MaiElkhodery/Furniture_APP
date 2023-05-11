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
                productDAO.insert(new Product(R.drawable.yellow_sofa,"Modern Sofas","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",450, FurnitureCategory.SOFA.getName()));
                productDAO.insert(new Product(R.drawable.seater_sofa,"Modern Sofas","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",850,FurnitureCategory.SOFA.getName()));
                productDAO.insert(new Product(R.drawable.table_lamp,"Modern Table Lamp","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",490,FurnitureCategory.LAMP.getName()));
                productDAO.insert(new Product(R.drawable.modern_velvet,"Modern Chair","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",390,FurnitureCategory.CHAIR.getName()));
                productDAO.insert(new Product(R.drawable.bed1,"Classic Bed","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",1150,FurnitureCategory.BED.getName()));
                productDAO.insert(new Product(R.drawable.lounge_chair,"Modern Chair","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",610,FurnitureCategory.CHAIR.getName()));
                productDAO.insert(new Product(R.drawable.modern_side_table,"Round Table","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",370,FurnitureCategory.TABLE.getName()));
                productDAO.insert(new Product(R.drawable.modern_sofa,"Modern Chair","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",965,FurnitureCategory.CHAIR.getName()));
                productDAO.insert(new Product(R.drawable.origami_table,"Origami Table","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",570,FurnitureCategory.TABLE.getName()));
                productDAO.insert(new Product(R.drawable.round_table,"Round Table","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",880,FurnitureCategory.TABLE.getName()));
                productDAO.insert(new Product(R.drawable.round_table2,"Round Table","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",600,FurnitureCategory.TABLE.getName()));
                productDAO.insert(new Product(R.drawable.sofa,"Modern Sofas","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",730,FurnitureCategory.SOFA.getName()));
                productDAO.insert(new Product(R.drawable.swoon_chair,"Modern Chair","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",180,FurnitureCategory.CHAIR.getName()));
                productDAO.insert(new Product(R.drawable.twist_coffe_table,"Modern Coffee Table","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",230,FurnitureCategory.TABLE.getName()));
                productDAO.insert(new Product(R.drawable.twist_coffe_table2,"Modern Coffee Table","Modern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern " +
                        "SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern SofasModern Sofas",255,FurnitureCategory.TABLE.getName()));
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
