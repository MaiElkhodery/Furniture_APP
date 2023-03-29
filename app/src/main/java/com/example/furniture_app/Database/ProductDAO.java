package com.example.furniture_app.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;

@Dao
public interface ProductDAO {
    @Insert
    void insert(Product product);
    @Delete
    void delete(Product product);
    @Update
    void update(Product product);
    @Query("SELECT * FROM product")
    LiveData<ArrayList<Product>> getAllProducts();
    @Query("SELECT * FROM Product WHERE name=:name")
    Product getProduct(String name);
}
