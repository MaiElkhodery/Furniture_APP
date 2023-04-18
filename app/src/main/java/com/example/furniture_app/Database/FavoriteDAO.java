package com.example.furniture_app.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface FavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoriteProducts product);
    @Delete
    void delete(FavoriteProducts product);
    @Query("SELECT * FROM FavoriteProducts")
    LiveData<List<FavoriteProducts>> getAllFavProducts();
}
