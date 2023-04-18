package com.example.furniture_app.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class SharedViewModel extends ViewModel{
    private MutableLiveData<List<FavoriteProducts>> fav_products = new MediatorLiveData<>();
    public SharedViewModel(@NonNull Application application) {
        super(application);
    }
    public void setListOfFavProducts(List<FavoriteProducts> products){
        fav_products = new MediatorLiveData<>();
        fav_products.setValue(products);
    }
    public LiveData<List<FavoriteProducts>> getListOfFavProducts(){
        return fav_products;
    }

}
