package com.example.furniture_app.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavoriteProducts {
    @PrimaryKey(autoGenerate = true)
    long id;
    int img;
    String name;
    String price;

    public FavoriteProducts(int img, String name, String price) {
        this.img = img;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
