package com.example.furniture_app.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);
    @Delete
    void delete(Product product);
    @Update
    void update(Product product);
    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAllProducts();
    @Query("SELECT * FROM Product WHERE name=:name")
    Product getProduct(String name);
}
