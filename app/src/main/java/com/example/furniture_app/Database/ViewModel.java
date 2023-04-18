package com.example.furniture_app.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private  LiveData<List<Product>> allProducts;
    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        allProducts= repository.getAllProducts();
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
    public User getUserById(){
        return repository.getUserById();
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
    public LiveData<List<Product>> getAllProducts(){
        return allProducts;
    }

    public LiveData<List<Product>> getAllFavProducts(){
        return repository.getAllFavProducts();
    }
}
