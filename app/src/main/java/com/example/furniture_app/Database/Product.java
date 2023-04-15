package com.example.furniture_app.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    long id;
    int img;
    String name;

    String description;
    String price;
    boolean inFavorite = false;


    @Ignore
    public Product(int img, String name, String price, boolean inFavorite) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.inFavorite = inFavorite;
    }

    @Ignore
    public Product(int img, String name, String price) {
        this.img = img;
        this.name = name;
        this.price = price;
    }
    public Product(long id, int img, String name, String description, String price) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isInFavorite() {
        return inFavorite;
    }

    public void setInFavorite(boolean inFavorite) {
        this.inFavorite = inFavorite;
    }
}
