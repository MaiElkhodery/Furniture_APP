package com.example.furniture_app.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private final UserDAO userDAO;
    private final ProductDAO productDAO;
    private LiveData<List<Product>> allProducts;

    public Repository(Application application){
        Database database = Database.getINSTANCE(application);
        userDAO= database.userDAO();
        productDAO= database.productDAO();
        allProducts= productDAO.getAllProducts();
    }
    public void insertUser(User user){
        new InsertUserAsyncTask(userDAO).execute(user);
    }
    public void deleteUser(User user){
        new DeleteUserAsyncTask(userDAO).execute(user);
    }
    public void updateUser(User user){
        new UpdateUserAsyncTask(userDAO).execute(user);
    }
    public User getUser(String email){
        return userDAO.getUser(email);
    }
    public User getUserById(){
        return userDAO.getUserById();
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDAO userDAO;
        private InsertUserAsyncTask(UserDAO userDAO){
            this.userDAO=userDAO;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDAO.insertUser(users[0]);
            return null;
        }
    }
    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDAO userDAO;
        private DeleteUserAsyncTask(UserDAO userDAO){
            this.userDAO=userDAO;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDAO.deleteUser(users[0]);
            return null;
        }
    }
    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDAO userDAO;
        private UpdateUserAsyncTask(UserDAO userDAO){
            this.userDAO=userDAO;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDAO.updateUserData(users[0]);
            return null;
        }
    }


    public void insertProduct(Product product){
        new InsertProductAsyncTask(productDAO).execute(product);
    }
    public void deleteProduct(Product product){
        new DeleteProductAsyncTask(productDAO).execute(product);
    }
    public void updateProduct(Product product){
        new UpdateProductAsyncTask(productDAO).execute(product);
    }
    public LiveData<List<Product>> getAllProducts(){
        return allProducts;
    }
    public LiveData<List<Product>> getProductOfType(String category) {
        return productDAO.getProductOfType(category);
    }
    public LiveData<List<Product>> getAllFavProducts(){
        return productDAO.getAllFavProducts();
    }
    public LiveData<List<Product>> getProduct(String name){
        return productDAO.getProduct("%"+name+"%");
    }

    public LiveData<List<Product>> getProductsInCart(){
        return productDAO.getProductsInShoppingCart();
    }

    private static class InsertProductAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDAO productDAO;
        private InsertProductAsyncTask(ProductDAO productDAO){
            this.productDAO=productDAO;
        }
        @Override
        protected Void doInBackground(Product... products) {
            productDAO.insert(products[0]);
            return null;
        }
    }
    private static class DeleteProductAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDAO productDAO;
        private DeleteProductAsyncTask(ProductDAO productDAO){
            this.productDAO=productDAO;
        }
        @Override
        protected Void doInBackground(Product... products) {
            productDAO.delete(products[0]);
            return null;
        }
    }
    private static class UpdateProductAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDAO productDAO;
        private UpdateProductAsyncTask(ProductDAO productDAO){
            this.productDAO=productDAO;
        }
        @Override
        protected Void doInBackground(Product... products) {
            productDAO.update(products[0]);
            return null;
        }
    }

}
