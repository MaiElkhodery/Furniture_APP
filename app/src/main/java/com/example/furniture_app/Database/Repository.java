package com.example.furniture_app.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private final UserDAO userDAO;
    private final ProductDAO productDAO;
    private final FavoriteDAO favoriteDAO;
    private LiveData<List<Product>> allProducts;
    private LiveData<List<FavoriteProducts>> allFavProducts;

    public Repository(Application application){
        Database database = Database.getINSTANCE(application);
        userDAO= database.userDAO();
        productDAO= database.productDAO();
        favoriteDAO=database.favoriteDAO();
        allProducts= productDAO.getAllProducts();
        allFavProducts=favoriteDAO.getAllFavProducts();
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
    public Product getProduct(String name){
        return productDAO.getProduct(name);
    }
    public LiveData<List<Product>> getAllProducts(){
        return allProducts;
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


    public void insertProductToFav(FavoriteProducts product){
        new InsertFavProductAsyncTask(favoriteDAO).execute(product);
    }
    public void deleteProductFromFav(FavoriteProducts product){
        new DeleteFavProductAsyncTask(favoriteDAO).execute(product);
    }
    public LiveData<List<FavoriteProducts>> getFavProducts(){
        return allFavProducts;
    }
    private static class InsertFavProductAsyncTask extends AsyncTask<FavoriteProducts, Void, Void> {

        private FavoriteDAO favoriteDAO;
        private InsertFavProductAsyncTask(FavoriteDAO favoriteDAO){
            this.favoriteDAO=favoriteDAO;
        }
        @Override
        protected Void doInBackground(FavoriteProducts... products) {
            favoriteDAO.insert(products[0]);
            return null;
        }
    }
    private static class DeleteFavProductAsyncTask extends AsyncTask<FavoriteProducts, Void, Void> {

        private FavoriteDAO favoriteDAO;
        private DeleteFavProductAsyncTask(FavoriteDAO favoriteDAO){
            this.favoriteDAO=favoriteDAO;
        }
        @Override
        protected Void doInBackground(FavoriteProducts... products) {
            favoriteDAO.delete(products[0]);
            return null;
        }
    }

}
