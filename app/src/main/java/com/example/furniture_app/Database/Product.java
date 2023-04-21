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
    int price;
    String category;
    boolean inFavorite = false;
    boolean isProductInCart = false;
    int quantity =0;


    @Ignore
    public Product(int img, String name, int price, boolean inFavorite,String category) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.inFavorite = inFavorite;
        this.category=category;
    }

    public Product(int img, String name, int price,String category) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.category=category;
    }
    @Ignore
    public Product(long id, int img, String name, String description, int price,String category) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category=category;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsProductInCart() {
        return isProductInCart;
    }

    public void setIsProductInCart(boolean addedToCart) {
        isProductInCart = addedToCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(isProductInCart){
            this.quantity = quantity;
        }else{
            this.quantity=0;
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isInFavorite() {
        return inFavorite;
    }

    public void setInFavorite(boolean inFavorite) {
        this.inFavorite = inFavorite;
    }
}
