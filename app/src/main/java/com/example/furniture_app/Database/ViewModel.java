package com.example.furniture_app.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }
    public void insertUser(User user){
        repository.insertUser(user);
    }
    public void updateUser(User user){
        repository.updateUser(user);
    }
    public void deleteUser(User user){
        repository.deleteUser(user);
    }
    public User getUser(String email){
        return repository.getUser(email);
    }

    public void insertProduct(Product product){
        repository.insertProduct(product);
    }
    public void updateProduct(Product product){
        repository.updateProduct(product);
    }
    public void deleteProduct(Product product){
        repository.deleteProduct(product);
    }
    public LiveData<ArrayList<Product>> getAllProducts(){
        return repository.getAllProducts();
    }
}
